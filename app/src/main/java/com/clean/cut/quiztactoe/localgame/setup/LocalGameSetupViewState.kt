package com.clean.cut.quiztactoe.localgame.setup

import com.clean.cut.quiztactoe.domain.SetupGameForm
import com.clean.cut.quiztactoe.model.Player

data class LocalGameSetupViewState(
  val startGameEnabled: Boolean = false,
  val renderValidationErrors: Boolean = false,
  val validation: SetupGameForm.Validation = SetupGameForm.Validation(),
  val player1: Player? = null,
  val player2: Player? = null,
  val rowCount: Int = 3,
  val columnCount: Int = 3
)