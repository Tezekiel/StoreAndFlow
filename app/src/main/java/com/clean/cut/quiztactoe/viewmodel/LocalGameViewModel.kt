package com.clean.cut.quiztactoe.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clean.cut.quiztactoe.objects.Cell

import com.clean.cut.quiztactoe.objects.Game
import com.clean.cut.quiztactoe.objects.Player
import com.clean.cut.quiztactoe.view.LocalGameSetupActivity

class LocalGameViewModel : ViewModel() {
    private var rowCount: Int = 0
    private var columnCount: Int = 0
    private var player1Name: String = ""
    private var player2Name: String = ""
    private lateinit var game: Game
    val cells = MutableLiveData<Array<Array<Cell?>>>()


    fun init(rowCount: Int, columnCount: Int, player1: Player, player2: Player) {
        this.rowCount = rowCount
        this.columnCount = columnCount
        this.player1Name = player1.name
        this.player2Name = player2.name

        game = Game(rowCount, columnCount, player1Name, player2Name)
        cells.value = game.cells
    }

    fun onItemClickListener(parent: ViewGroup, view: View, position: Int, id: Long) {

        val row = position / columnCount
        val column = position % columnCount

        val temp: Array<Array<Cell?>> = cells.value!!
        val clickedCell: Cell? = temp[row][column]
        if (clickedCell == null || clickedCell.isEmpty()) {
            temp[row][column] = Cell(game.currentPlayer)
            cells.value = temp
            if(game.hasGameEnded()){
                Log.v("primjer", "game ended")
                game.reset()
            }else{
                game.switchPlayer()
            }

        }
    }

    fun getWinner(): LiveData<Player> {
        return game.winner
    }
    fun winOrDraw(): LiveData<String> {
        return game.winOrDraw
    }


}
