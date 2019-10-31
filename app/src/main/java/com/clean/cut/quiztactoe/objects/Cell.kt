package com.clean.cut.quiztactoe.objects

class Cell (var player: Player){
    fun isEmpty(): Boolean {
        return (player == null || player.sign.isNullOrEmpty())
    }
}