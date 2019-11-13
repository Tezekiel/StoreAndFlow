package com.clean.cut.quiztactoe.objects

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlin.collections.ArrayList


class Game(
    var rowCount: Int,
    var columnCount: Int,
    var player1Name: String,
    var player2Name: String
) {

    var cells: Array<Array<Cell?>> = Array(rowCount) { arrayOfNulls<Cell>(columnCount) }
    var player1 = Player(player1Name, "x")
    var player2 = Player(player2Name, "o")
    var currentPlayer = player1
    var winner: MutableLiveData<Player> = MutableLiveData()
    var winOrDraw: MutableLiveData<String> = MutableLiveData()

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun hasGameEnded(): Boolean {
        if (hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()) {
            winner.value = currentPlayer
            winOrDraw.value = "win"
            return true
        }

        if (isBoardFull()) {
            Log.v("primjer", "Board full")
            winner.value = null
            winOrDraw.value = "draw"
            return true
        }

        return false
    }

    private fun isBoardFull(): Boolean {
        return !cells.flatten().any { it == null }
    }
    private fun hasHorizontalWin(): Boolean {
        for (row in cells) {
            if (areEqual(row.toList() as ArrayList<Cell?>)) {
                Log.v("primjer", "Horizontal win")
                return true
            }
        }
        return false
    }
    private fun hasVerticalWin(): Boolean {
        val columnList: ArrayList<Cell?> = arrayListOf()
        for (column in cells[0].indices) {
            for (row in cells.indices) {
                columnList.add(cells[row][column])
            }
            if (areEqual(columnList)) {
                Log.v("primjer", "Vertical win")
                return true
            }
            columnList.clear()
        }

        return false
    }
    private fun hasDiagonalWin(): Boolean {
        val diagonals = arrayListOf<ArrayList<Cell?>>()
        for (row in rowCount - 1 downTo 0) {
            diagonals.add(getDiagonal(row, 0))
        }
        for (col in 0 until columnCount) {
            diagonals.add(getDiagonal(0, col))
        }
        for (row in rowCount - 1 downTo 0) {
            diagonals.add(getDiagonalReverse(row))
        }
        for (col in 0 until columnCount) {
            diagonals.add(getDiagonalReverse2(col))
        }

        diagonals.forEach {
            if (areEqual(it)) {
                Log.v("primjer", "Diagonal win")
                return true
            }
        }
        return false
    }
    private fun getDiagonal(_x: Int, _y: Int): ArrayList<Cell?> {
        var x = _x
        var y = _y
        val diagonal = arrayListOf<Cell?>()
        while (x < rowCount && y < columnCount) {
            diagonal.add(cells[x++][y++])
        }
        return diagonal
    }
    private fun getDiagonalReverse(_x: Int): ArrayList<Cell?> {
        var x = _x
        var y = 0
        val diagonal = arrayListOf<Cell?>()
        while (y < columnCount && x >= 0) {
            diagonal.add(cells[x][y])
            x--
            y++
        }
        return diagonal
    }
    private fun getDiagonalReverse2(_y: Int): ArrayList<Cell?> {
        var x = 0
        var y = _y
        val diagonal = arrayListOf<Cell?>()
        while (x < rowCount && y >= 0) {
            diagonal.add(cells[x][y])
            x++
            y--
        }
        return diagonal
    }
    private fun areEqual(cells: ArrayList<Cell?>): Boolean {
        for (i in 0 until cells.size - 2) {
            if (cells[i]?.player?.sign.equals(cells[i + 1]?.player?.sign) &&
                cells[i + 1]?.player?.sign.equals(cells[i + 2]?.player?.sign) &&
                cells[i] != null) {
                return true
            }
        }
        return false
    }

}