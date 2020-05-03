package com.clean.cut.quiztactoe

import com.clean.cut.quiztactoe.model.Cell
import com.clean.cut.quiztactoe.model.Player

val player1 = Player("test1", "x")
val player2 = Player("test2", "y")

val boardStateVerticalWin = mutableListOf(
  Cell(null), Cell(player1), Cell(null),
  Cell(null), Cell(player1), Cell(null),
  Cell(null), Cell(player1), Cell(null)
)

val boardStateHorizontalWin = mutableListOf(
  Cell(player1), Cell(player1), Cell(player1),
  Cell(null), Cell(null), Cell(null),
  Cell(null), Cell(player1), Cell(null)
)

val boardStateDiagonalDownWin = mutableListOf(
  Cell(player1), Cell(null), Cell(null), Cell(null), Cell(null),
  Cell(null), Cell(player1), Cell(null), Cell(player1), Cell(null),
  Cell(null), Cell(player2), Cell(player2), Cell(null), Cell(player1),
  Cell(null), Cell(null), Cell(null), Cell(player2), Cell(null),
  Cell(null), Cell(null), Cell(player1), Cell(player2), Cell(player2)
)

val boardStateDiagonalUpWin = mutableListOf(
  Cell(player1), Cell(null), Cell(null), Cell(null), Cell(null),
  Cell(null), Cell(null), Cell(null), Cell(null), Cell(null),
  Cell(null), Cell(null), Cell(null), Cell(null), Cell(player1),
  Cell(null), Cell(player1), Cell(null), Cell(player1), Cell(null),
  Cell(null), Cell(null), Cell(player1), Cell(null), Cell(null)
)

val boardStateDiagonalUpWin2 = mutableListOf(
  Cell(null), Cell(null), Cell(null),
  Cell(null), Cell(null), Cell(player2),
  Cell(null), Cell(player2), Cell(null),
  Cell(player2), Cell(null), Cell(null),
  Cell(null), Cell(null), Cell(null)
)

val noWin = mutableListOf(
  Cell(null), Cell(player1), Cell(player2),
  Cell(null), Cell(player2), Cell(null),
  Cell(player1), Cell(player1), Cell(null)
)