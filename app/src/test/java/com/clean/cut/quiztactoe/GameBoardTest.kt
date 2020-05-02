package com.clean.cut.quiztactoe

import com.clean.cut.quiztactoe.model.Game
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class GameBoardTest {
  @Test
  fun `vertical line win`(){
    val game = Game(cells = boardStateVerticalWin, currentPlayer = player1, otherPlayer = player2, rows = 3, columns = 3)
    assertTrue(game.checkAllLines())
  }

  @Test
  fun `horizontal line win`(){
    val game = Game(cells = boardStateHorizontalWin, currentPlayer = player1, otherPlayer = player2, rows = 3, columns = 3)
    assertTrue(game.checkAllLines())
  }

  @Test
  fun `diagonal down line win`(){
    val game = Game(cells = boardStateDiagonalDownWin, currentPlayer = player1, otherPlayer = player2, rows = 5, columns = 5)
    assertTrue(game.checkAllLines())
  }

  @Test
  fun `diagonal up line win`(){
    val game = Game(cells = boardStateDiagonalUpWin, currentPlayer = player1, otherPlayer = player2, rows = 5, columns = 5)
    assertTrue(game.checkAllLines())
  }

  @Test
  fun `no win`(){
    val game = Game(cells = noWin, currentPlayer = player1, otherPlayer = player2, rows = 3, columns = 3)
    assertFalse(game.checkAllLines())
  }
}
