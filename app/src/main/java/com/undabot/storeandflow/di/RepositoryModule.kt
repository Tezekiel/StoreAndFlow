package com.undabot.storeandflow.di

import com.undabot.storeandflow.data.repository.MessagesDataSource
import com.undabot.storeandflow.data.repository.MessagesRepositoryImpl
import com.undabot.storeandflow.domain.repository.MessagesRepository
import org.koin.dsl.module

val repositoryModule = module {
  single<MessagesRepository> { MessagesRepositoryImpl(get()) }
  single { MessagesDataSource() }
}