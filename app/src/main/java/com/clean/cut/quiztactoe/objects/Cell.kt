package com.clean.cut.quiztactoe.objects

class Cell (var player: Player){
    fun isEmpty(): Boolean {
        return player.sign.isNullOrEmpty()
    }
}