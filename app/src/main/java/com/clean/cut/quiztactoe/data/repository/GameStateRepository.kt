package com.clean.cut.quiztactoe.data.repository

import com.clean.cut.quiztactoe.model.Cell
import com.clean.cut.quiztactoe.model.Game
import com.clean.cut.quiztactoe.model.Player

class GameStateRepository {

  lateinit var gameState: Game

  fun cellClicked(pos: Int) {
    gameState = gameState.copy(pendingCellPos = pos)
  }

  fun questionAnswered(isCorrect: Boolean): Game{
    gameState = gameState.copy(writeOrNext = isCorrect)
    return gameState
  }

  fun newGame(row: Int, column: Int, currentPlayer: Player, otherPlayer: Player) {
    gameState = Game(
      cells = MutableList(row * column) { Cell(null) },
      currentPlayer = currentPlayer,
      otherPlayer = otherPlayer,
      rows = row,
      columns = column
    )
  }

  fun restartGame(): Game {
    gameState = Game(
      cells = MutableList(gameState.rows * gameState.rows) { Cell(null) },
      currentPlayer = gameState.otherPlayer,
      otherPlayer = gameState.currentPlayer,
      rows = gameState.rows,
      columns = gameState.columns
    )
    return gameState
  }

}