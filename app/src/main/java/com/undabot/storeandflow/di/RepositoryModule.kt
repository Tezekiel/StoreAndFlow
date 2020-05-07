package com.undabot.storeandflow.di

import com.undabot.storeandflow.data.repository.QuestionsDataSource
import com.undabot.storeandflow.data.repository.QuestionsRepositoryImpl
import com.undabot.storeandflow.domain.repository.QuestionsRepository
import org.koin.dsl.module

val repositoryModule = module {
  single<QuestionsRepository> { QuestionsRepositoryImpl(get()) }
  single { QuestionsDataSource(get()) }
}