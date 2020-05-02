package com.clean.cut.quiztactoe.localgame.setup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clean.cut.quiztactoe.data.store.UserStore
import com.clean.cut.quiztactoe.domain.SetupGameForm
import com.clean.cut.quiztactoe.domain.SetupGameForm.Validation
import com.clean.cut.quiztactoe.model.Player
import com.clean.cut.quiztactoe.util.SingleLiveEvent
import com.clean.cut.quiztactoe.util.mutate

class LocalGameSetupViewModel(val store: UserStore) : ViewModel() {

  val state = MutableLiveData(LocalGameSetupViewState())
  val userNameAvailable = SingleLiveEvent<String>()

  init {
    if (store.getName().isNotBlank()) userNameAvailable.value = store.getName()
  }

  fun startGameClicked(gameForm: SetupGameForm) {
    state.mutate { copy(renderValidationErrors = false, startGameEnabled = false) }
    val validation = validateData(gameForm)
    state.mutate { copy(renderValidationErrors = true, validation = validation) }

    if (validation.isValid()) {
      state.mutate {
        copy(
          startGameEnabled = true,
          player1 = Player(gameForm.player1Name, "x"),
          player2 = Player(gameForm.player2Name, "o"),
          rowCount = gameForm.rowCount,
          columnCount = gameForm.columnCount
        )
      }
    }
  }

  private fun validateData(gameForm: SetupGameForm): Validation {
    return with(gameForm) {
      Validation(
        player1NameValid = this.player1Name.isNotBlank(),
        player2NameValid = this.player2Name.isNotBlank(),
        rowsValid = this.rowCount in 3..6,
        columnsValid = this.columnCount in 3..6
      )
    }
  }

}