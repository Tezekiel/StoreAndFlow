package com.clean.cut.quiztactoe.di

import com.clean.cut.quiztactoe.localgame.game.LocalGameViewModel
import com.clean.cut.quiztactoe.localgame.setup.LocalGameSetupViewModel
import com.clean.cut.quiztactoe.login.LoginViewModel
import com.clean.cut.quiztactoe.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { LoginViewModel(get()) }
  viewModel { MenuViewModel(get()) }
  viewModel { LocalGameSetupViewModel(get()) }
  viewModel { LocalGameViewModel(get(), get()) }
}
