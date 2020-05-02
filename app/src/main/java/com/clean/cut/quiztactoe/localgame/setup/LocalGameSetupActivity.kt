package com.clean.cut.quiztactoe.localgame.setup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.domain.SetupGameForm
import com.clean.cut.quiztactoe.localgame.game.LocalGameActivity
import com.clean.cut.quiztactoe.util.setSafeOnClickListener
import com.clean.cut.quiztactoe.util.toText
import kotlinx.android.synthetic.main.activity_local_game_setup.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocalGameSetupActivity : AppCompatActivity() {
  private val viewModel by viewModel<LocalGameSetupViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_local_game_setup)

    // set up toolbar
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { finish() }

    //listeners
    btn_play.setSafeOnClickListener { viewModel.startGameClicked(getDataFromViews()) }

    viewModel.state.observe(this, Observer { render(it) })
    viewModel.userNameAvailable.observe(this, Observer { player1NameEt.setText(it) })
  }

  private fun getDataFromViews(): SetupGameForm =
    SetupGameForm(
      player1NameEt.toText(),
      player2NameEt.toText(),
      rowNumberEt.toText().toInt(),
      columnNumberEt.toText().toInt()
    )


  private fun render(state: LocalGameSetupViewState) {
    if (state.renderValidationErrors) {
      with(state.validation) {
        player1NameIL.error = if (!player1NameValid) getString(R.string.error_empty) else null
        player2NameIL.error = if (!player2NameValid) getString(R.string.error_empty) else null
        rowNumberIL.error = if (!rowsValid) getString(R.string.error_between_3_6) else null
        columnNumberIL.error = if (!columnsValid) getString(R.string.error_between_3_6) else null
      }
    }

    if (state.startGameEnabled) {
      startActivity(
        LocalGameActivity.with(
          state.player1!!,
          state.player2!!,
          state.rowCount,
          state.columnCount,
          this
        )
      )
    }
  }
}
