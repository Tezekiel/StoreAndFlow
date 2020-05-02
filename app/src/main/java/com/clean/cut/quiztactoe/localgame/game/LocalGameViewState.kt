package com.clean.cut.quiztactoe.localgame.game

import com.clean.cut.quiztactoe.model.Game
import com.clean.cut.quiztactoe.model.Player
import com.clean.cut.quiztactoe.model.Question

sealed class LocalGameViewState {
  data class Board(val game: Game? = null) : LocalGameViewState()
  data class Questions(val questions: List<Question>) : LocalGameViewState()
  data class Quiz(val question: Question, val currentPlayer: Player, val answered: Boolean = false) : LocalGameViewState()
  object Error : LocalGameViewState()
  object Loading : LocalGameViewState()
}
