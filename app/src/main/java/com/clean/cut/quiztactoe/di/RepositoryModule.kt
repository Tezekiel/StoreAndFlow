package com.clean.cut.quiztactoe.di

import android.content.Context
import android.content.SharedPreferences
import com.clean.cut.quiztactoe.data.repository.GameStateRepository
import com.clean.cut.quiztactoe.data.repository.QuestionsRepository
import com.clean.cut.quiztactoe.data.store.UserStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
  single { QuestionsRepository(get()) }
  single { GameStateRepository() }
}

val storeModule = module {
  single { UserStore(get()) }

  fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("quiz-tac-toe", Context.MODE_PRIVATE);
  }

  single { provideSharedPreferences(androidApplication()) }
}