package com.clean.cut.quiztactoe.di

import com.clean.cut.quiztactoe.ui.AnswersFactory
import com.clean.cut.quiztactoe.ui.CategoryItemFactory
import org.koin.dsl.module

val factoryModule = module {
  single { CategoryItemFactory() }
  single { AnswersFactory() }
}