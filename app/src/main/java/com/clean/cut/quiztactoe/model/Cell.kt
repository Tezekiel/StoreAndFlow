package com.clean.cut.quiztactoe.model

data class Cell(private val player: Player?) {
  val state = player?.sign ?: ""
}