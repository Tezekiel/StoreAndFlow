package com.clean.cut.quiztactoe.localgame.game

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.cut.quiztactoe.data.repository.GameStateRepository
import com.clean.cut.quiztactoe.data.repository.QuestionsRepository
import com.clean.cut.quiztactoe.data.resources.Result
import com.clean.cut.quiztactoe.localgame.game.LocalGameViewState.*
import com.clean.cut.quiztactoe.model.Cell
import com.clean.cut.quiztactoe.model.GameState
import com.clean.cut.quiztactoe.model.Player
import com.clean.cut.quiztactoe.model.Question
import com.clean.cut.quiztactoe.util.SingleLiveEvent
import com.clean.cut.quiztactoe.util.mutate
import kotlinx.coroutines.launch

class LocalGameViewModel(
  private val repository: QuestionsRepository,
  private val gameStateRepository: GameStateRepository
) : ViewModel() {
  val state = MutableLiveData<LocalGameViewState>(Board())
  val cellOccupied = SingleLiveEvent<Unit>()
  val colorButton = SingleLiveEvent<Pair<View, Boolean>>()

  fun intentDataAvailable(rowCount: Int, columnCount: Int, player1: Player, player2: Player) {
    gameStateRepository.newGame(rowCount, columnCount, player1, player2)
    state.mutate { Board(gameStateRepository.gameState) }
  }

  fun newGameStarted()= state.mutate { Board(gameStateRepository.restartGame()) }

  fun cellClicked(cell: Cell, pos: Int) {
    if ((state.value as Board).game?.state == GameState.PLAYING) {
      if (cell.state.isNotBlank()) {
        cellOccupied.call()
      } else {
        gameStateRepository.cellClicked(pos)
        getQuestions()
      }
    }
  }

  private fun getQuestions() {
    viewModelScope.launch {
      state.mutate { Loading }
      when (val result = repository.getQuestions()) {
        is Result.Success -> state.mutate { Questions(result.data) }
        is Result.Error -> state.mutate { Error }
      }
    }
  }

  fun categoryPicked(question: Question) = state.mutate { Quiz(question, gameStateRepository.gameState.currentPlayer) }

  fun answerSelected(button: View, isCorrect: Boolean) {
    // only trigger if not answered already
    if (!(state.value as Quiz).answered) {
      state.mutate { (this as Quiz).copy(answered = true) }
      gameStateRepository.questionAnswered(isCorrect)
      colorButton.value = Pair(button, isCorrect)
    }
  }

  fun backToGameClicked()= state.mutate { Board(gameStateRepository.gameState) }

}
