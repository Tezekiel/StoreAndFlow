package com.clean.cut.quiztactoe.data.store

import android.content.SharedPreferences
import com.clean.cut.quiztactoe.util.string

class UserStore(preferences: SharedPreferences) {

  private var userName by preferences.string(key = "user_name")

  fun setName(name: String) {
    userName = name
  }

  fun getName() = userName!!
}