package com.clean.cut.quiztactoe.login

import androidx.lifecycle.ViewModel
import com.clean.cut.quiztactoe.data.store.UserStore
import com.clean.cut.quiztactoe.util.SingleLiveEvent

class LoginViewModel(private val userStore: UserStore) : ViewModel() {

  val error = SingleLiveEvent<Unit>()
  val login = SingleLiveEvent<Unit>()
  val savedName = SingleLiveEvent<String>()

  init {
    savedName.value = userStore.getName()
  }

  fun logInCLicked(text: String) {
    if (text.isNotEmpty()) {
      userStore.setName(text)
      login.call()
    } else {
      error.call()
    }
  }
}