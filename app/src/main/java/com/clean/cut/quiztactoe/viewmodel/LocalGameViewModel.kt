package com.clean.cut.quiztactoe.viewmodel

import androidx.lifecycle.ViewModel

import com.clean.cut.quiztactoe.objects.Game
import com.clean.cut.quiztactoe.objects.Player

class LocalGameViewModel(
    rowCount: Int,
    columnCount: Int,
    player1Name: String,
    player2Name: String
): ViewModel(){
    var game = Game(rowCount, columnCount, player1Name, player2Name)


}
