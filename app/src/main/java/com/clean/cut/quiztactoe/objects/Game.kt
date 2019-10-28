package com.clean.cut.quiztactoe.objects

import androidx.lifecycle.MutableLiveData

class Game(
    var rowCount: Int,
    var columnCount: Int,
    var player1Name: String,
    var player2Name: String)
{

    var cells: Array<Array<Cell?>> = Array(rowCount) { arrayOfNulls<Cell>(columnCount) }
    var player1 = Player(player1Name, "x")
    var player2 = Player(player2Name, "o")
    var currentPlayer = player1
    var winner: MutableLiveData<Player> = MutableLiveData()

    fun switchPlayer(){
        currentPlayer = if(currentPlayer == player1) player2 else player1
    }
}