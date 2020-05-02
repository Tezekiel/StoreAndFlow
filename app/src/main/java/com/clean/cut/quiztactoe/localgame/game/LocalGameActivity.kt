package com.clean.cut.quiztactoe.localgame.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent.ACTION_MOVE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.localgame.game.adapters.GameBoardGridAdapter
import com.clean.cut.quiztactoe.model.GameState
import com.clean.cut.quiztactoe.model.Player
import com.clean.cut.quiztactoe.ui.AnswersFactory
import com.clean.cut.quiztactoe.ui.CategoryItemFactory
import com.clean.cut.quiztactoe.util.getDeviceWidth
import com.clean.cut.quiztactoe.util.setSafeOnClickListener
import com.clean.cut.quiztactoe.util.snack
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_local_game.*
import kotlinx.android.synthetic.main.questions_layout.*
import kotlinx.android.synthetic.main.quiz_layout.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocalGameActivity : AppCompatActivity() {
  private val viewModel by viewModel<LocalGameViewModel>()
  private val categoryItemFactory: CategoryItemFactory by inject()
  private val answersFactory: AnswersFactory by inject()

  companion object {
    fun with(
      player1: Player,
      player2: Player,
      rowCount: Int,
      columnCount: Int,
      context: Context
    ): Intent =
      Intent(context, LocalGameActivity::class.java)
        .putExtra("p1", player1)
        .putExtra("p2", player2)
        .putExtra("row", rowCount)
        .putExtra("column", columnCount)
  }

  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_local_game)

    // pass intent data to vm
    viewModel.intentDataAvailable(
      intent.getIntExtra("row", 3),
      intent.getIntExtra("column", 3),
      intent.getSerializableExtra("p1") as Player,
      intent.getSerializableExtra("p2") as Player
    )

    // set up adapter
    boardGv.apply {
      //gv looks and adapter
      val gvParams = boardGv.layoutParams
      gvParams.width = getDeviceWidth(windowManager) - 50
      gvParams.height = gvParams.width
      layoutParams = gvParams
      numColumns = intent.getIntExtra("column", 3)

      //listener (disable scrolling)
      setOnTouchListener { _, e -> e.action == ACTION_MOVE }

      adapter = GameBoardGridAdapter()
    }

    // listeners
    btn_play_again.setSafeOnClickListener { viewModel.newGameStarted() }

    // board listener
    (boardGv.adapter as GameBoardGridAdapter).listener = { cell, pos ->
      viewModel.cellClicked(cell, pos)
    }

    // observe viewModel
    viewModel.state.observe(this, Observer { render(it) })
    viewModel.cellOccupied.observe(this, Observer { view_animator.snack(getString(R.string.error_cell_occupied)) })
    viewModel.colorButton.observe(this, Observer { (it.first as MaterialButton).backgroundTintList = getColorList(it.second) })
  }

  private fun render(state: LocalGameViewState) {
    // change screens
    when (state) {
      // tic tac toe board
      is LocalGameViewState.Board -> {
        view_animator.displayedChildId = R.id.grid_board
        (boardGv.adapter as GameBoardGridAdapter).data = state.game?.cells ?: emptyList()
        when (state.game?.state) {
          GameState.PLAYING -> {
            tv_win_draw_play.text = getString(R.string.currently_playing)
            tv_player.text = state.game.currentPlayer.name
            btn_play_again.isVisible = false
          }
          GameState.WIN -> {
            tv_win_draw_play.text = getString(R.string.winner)
            tv_player.text = state.game.currentPlayer.name
            btn_play_again.isVisible = true
          }
          GameState.DRAW -> {
            tv_win_draw_play.text = getString(R.string.draw)
            tv_player.text = ""
            btn_play_again.isVisible = true
          }
          null -> {
          }
        }
      }

      // question category selection screen
      is LocalGameViewState.Questions -> {
        view_animator.displayedChildId = R.id.questions
        categoryItemFactory.into(ll_category_container, state.questions) {
          viewModel.categoryPicked(it)
        }
      }

      // quiz state (question and answers)
      is LocalGameViewState.Quiz -> {
        view_animator.displayedChildId = R.id.quiz
        tv_who_is_answering.text = state.currentPlayer.name
        if (!state.answered){
          answersFactory.into(ll_answers_container, state.question) { view, isCorrect -> viewModel.answerSelected(view, isCorrect) }
        }

        btn_back_to_game.apply {
          isVisible = state.answered
          setSafeOnClickListener { viewModel.backToGameClicked() }
        }
      }

      LocalGameViewState.Error -> view_animator.displayedChildId = R.id.error
      LocalGameViewState.Loading -> view_animator.displayedChildId = R.id.loading
    }
  }

  private fun getColorList(isCorrect: Boolean) =
    if (isCorrect) {
      this.resources.getColorStateList(R.color.lightest_green)
    } else {
      this.resources.getColorStateList(R.color.red)
    }
}
