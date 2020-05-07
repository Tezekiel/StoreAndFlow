package com.undabot.storeandflow.di

import com.undabot.storeandflow.questionbank.QuestionBankViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { QuestionBankViewModel(get(), get()) }
}
