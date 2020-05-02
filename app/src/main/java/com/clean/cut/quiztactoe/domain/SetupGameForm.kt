package com.clean.cut.quiztactoe.domain

data class SetupGameForm(
  val player1Name: String,
  val player2Name: String,
  val rowCount: Int,
  val columnCount: Int
) {
  data class Validation(
    val player1NameValid: Boolean = false,
    val player2NameValid: Boolean = false,
    val rowsValid: Boolean = false,
    val columnsValid: Boolean = false
  ) {
    fun isValid(): Boolean {
      return player1NameValid && player2NameValid && rowsValid && columnsValid
    }
  }
}
