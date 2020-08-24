package com.undabot.storeandflow.di

import com.undabot.storeandflow.messagedetail.MessageDetailViewModel
import com.undabot.storeandflow.messages.MessagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { MessagesViewModel(get()) }
  viewModel { MessageDetailViewModel(get(), get()) }
}
