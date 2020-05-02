package com.clean.cut.quiztactoe.menu

import androidx.lifecycle.ViewModel
import com.clean.cut.quiztactoe.data.store.UserStore
import com.clean.cut.quiztactoe.util.SingleLiveEvent

class MenuViewModel (userStore: UserStore) : ViewModel() {
  private val savedName = SingleLiveEvent<String>()

  init {
    savedName.value = userStore.getName()
  }

}