package com.undabot.storeandflow.di

import com.undabot.storeandflow.domain.interactors.GetQuestions
import com.undabot.storeandflow.domain.interactors.StreamQuestions
import org.koin.dsl.module

val interactorModule = module {
    single { StreamQuestions(get()) }
    single { GetQuestions(get()) }
}