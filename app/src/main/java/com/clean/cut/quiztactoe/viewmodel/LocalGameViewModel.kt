package com.clean.cut.quiztactoe.viewmodel

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clean.cut.quiztactoe.objects.Cell
import com.clean.cut.quiztactoe.objects.Game
import com.clean.cut.quiztactoe.objects.Player


class LocalGameViewModel : ViewModel() {
    private var rowCount: Int = 0
    private var columnCount: Int = 0
    private var player1Name: String = ""
    private var player2Name: String = ""
    lateinit var game: Game
    private var row = 0
    private var column = 0
    val cells = MutableLiveData<Array<Array<Cell?>>>()
    val currentlyPlaying = MutableLiveData<String>()
    val startQuizActivity = MutableLiveData<Boolean>()
    val gameOver = MutableLiveData<Boolean>(false)

    fun init(rowCount: Int, columnCount: Int, player1: Player, player2: Player) {
        this.rowCount = rowCount
        this.columnCount = columnCount
        this.player1Name = player1.name
        this.player2Name = player2.name

        startQuizActivity.value = false
        game = Game(rowCount, columnCount, player1Name, player2Name)
        cells.value = game.cells
        currentlyPlaying.value = game.currentPlayer.name
    }

    fun onItemClickListener(parent: ViewGroup, view: View, position: Int, id: Long) {
        row = position / columnCount
        column = position % columnCount

        val clickedCell: Cell? = cells.value?.get(row)?.get(column)

        if (clickedCell == null || clickedCell.isEmpty()) {
            startQuizActivity.value = true
        }
    }

    fun getWinner(): LiveData<Player> {
        return game.winner
    }
    fun winOrDraw(): LiveData<String> {
        return game.winOrDraw
    }

    fun questionAnswered(questionAnsweredCorrectly: Boolean) {
        if(questionAnsweredCorrectly) {
            cells.value!![row][column] = Cell(game.currentPlayer)
            cells.postValue(cells.value)
        }

        if (questionAnsweredCorrectly && game.hasGameEnded()) {
            gameOver.value = true
            game.reset()
        } else {
            game.switchPlayer()
            currentlyPlaying.value = game.currentPlayer.name
        }
    }


}
