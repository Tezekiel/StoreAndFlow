package com.clean.cut.quiztactoe.model

data class Game(
  var state: GameState = GameState.PLAYING,
  val cells: MutableList<Cell>,
  var currentPlayer: Player,
  var otherPlayer: Player,
  var pendingCellPos: Int? = null,
  var writeOrNext: Boolean? = null,
  var rows: Int = -1,
  var columns: Int = -1
) {

  init {
    when (writeOrNext) {
      null -> {
      }
      true -> {
        cells[pendingCellPos!!] = Cell(currentPlayer)
        checkBoardState()
      }
      false -> {
        checkBoardState()
      }
    }
  }

  private fun switchPlayers() {
    // switch places
    currentPlayer = otherPlayer.also { otherPlayer = currentPlayer }
  }

  private fun checkBoardState() {
    if (checkAllLines()){
      state = GameState.WIN
    }else{
      if (isItDraw()){
        state = GameState.DRAW
      }else{
        state = GameState.PLAYING
        switchPlayers()
      }
    }
    pendingCellPos = null
    writeOrNext = null
  }

  private fun isItDraw(): Boolean {
    return cells.none { it.state.isEmpty() }
  }

  fun checkAllLines(): Boolean {
    val twoDList = convertTo2dList()
    if (checkHorizontal(twoDList)) return true
    if (checkVertical(twoDList)) return true
    if (checkDiagonalDown(twoDList)) return true
    if (checkDiagonalUp(twoDList)) return true
    return false
  }

  private fun checkDiagonalUp(twoDList: List<List<Cell>>): Boolean {
    println(twoDList.size)
    println(twoDList[0].size)
    for (i in twoDList.size - 1 downTo 2) {
      inner@ for(j in 0 until twoDList[i].size - 2) {
        val cellToCheck = twoDList[i][j]
        //if cell empty go to next continue@inner
        println("indexes [$i,$j]")
        println("cell to check $cellToCheck")
        if (cellToCheck.state.isEmpty()) continue@inner
        //diagonal up
        if (cellToCheck == twoDList[i - 1][j + 1] && cellToCheck == twoDList[i - 2][j + 2]) {
          println("Found a diagonal up match")
          return true
        }
      }
    }
    return false
  }

  private fun checkDiagonalDown(twoDList: List<List<Cell>>): Boolean {
    for (i in 0 until twoDList.size - 2) {
      inner@ for (j in 0 until twoDList[i].size - 2) {
        val cellToCheck = twoDList[i][j]
        //if cell empty go to next continue@inner
        if (cellToCheck.state.isEmpty()) continue@inner
        //diagonal down
        if (cellToCheck == twoDList[i + 1][j + 1] && cellToCheck == twoDList[i + 2][j + 2]) {
          println("Found a diagonal down match")
          return true
        }
      }
    }
    return false
  }

  private fun checkVertical(twoDList: List<List<Cell>>): Boolean {
    for (i in  0 until twoDList.size -2) {
      inner@ for (j in 0 until twoDList[i].size) {
        val cellToCheck = twoDList[i][j]
        //if cell empty go to next
        if (cellToCheck.state.isEmpty()) continue@inner
        //vertical
        if (cellToCheck == twoDList[i + 1][j] && cellToCheck == twoDList[i + 2][j]) {
          println(twoDList)
          println("Found a vertical match")
          return true
        }
      }
    }
    return false
  }

  private fun checkHorizontal(twoDList: List<List<Cell>>): Boolean {
    for (i in 0 until twoDList.size) {
      inner@ for (j in 0 until twoDList[i].size - 2) {
        val cellToCheck = twoDList[i][j]

        //if cell empty go to next
        if (cellToCheck.state.isEmpty()) continue@inner

        //horizontal
        if (cellToCheck == twoDList[i][j + 1] && cellToCheck == twoDList[i][j + 2]) {
          println("Found a horizontal match")
          return true
        }

      }
    }
    return false
  }

  private fun convertTo2dList(): List<List<Cell>> = cells.chunked(columns)

}

enum class GameState { PLAYING, WIN, DRAW }
