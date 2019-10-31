package com.clean.cut.quiztactoe.objects

import android.os.Build.VERSION_CODES.*
import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.lang.IndexOutOfBoundsException
import java.util.*
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

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun hasGameEnded(): Boolean {
        if (hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin()) {
            winner.value = currentPlayer
            return true
        }

        if (isBoardFull()) {
            winner.value = null
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
                return true
            }
        }
        return false
    }

    private fun hasVerticalWin(): Boolean {
        val columnList: ArrayList<Cell?> = arrayListOf()
        for (column in cells[0].indices){
            for (row in cells.indices){
                columnList.add(cells[row][column])
            }
            if(areEqual(columnList)){
                return true
            }
            columnList.clear()
        }

        return false
    }

    private fun hasDiagonalWin(): Boolean {
        val diags = arrayListOf<ArrayList<Cell?>>()
        for (row in rowCount - 1 downTo 2) {
            diags.add(getDiagonal(row, 0))
        }
        for (col in 0 until columnCount) {
            diags.add(getDiagonal(0, col))
        }

        diags.forEach {if(areEqual(it)) return true}
        return false
    }

    private fun getDiagonal(_x: Int, _y: Int): ArrayList<Cell?> {
        var x = _x
        var y = _y
        val diag = arrayListOf<Cell?>()
        while (x < rowCount && y < columnCount) {
            diag.add(cells[x++][y++])
        }
        return diag
    }

    private fun areEqual(cells: ArrayList<Cell?>): Boolean {
        if (cells.isNullOrEmpty()) {
            return false
        }

        for (cell in cells) {
            if (cell?.player?.sign.isNullOrEmpty()) {
                return false
            }
        }

        for (i in 0 until cells.size) {
            try {
                if (cells[i]?.player?.sign.equals(cells[i + 1]?.player?.sign) &&
                    cells[i + 1]?.player?.sign.equals(cells[i + 2]?.player?.sign)
                ) {
                    return true
                }
            } catch (e: IndexOutOfBoundsException) {
                return false
            }
        }

        return false
    }

    fun reset() {
        //TODO
    }
}