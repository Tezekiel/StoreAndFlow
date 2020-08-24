package com.undabot.storeandflow.di

import com.undabot.storeandflow.domain.interactors.DeleteMessage
import com.undabot.storeandflow.domain.interactors.StreamMessages
import org.koin.dsl.module

val interactorModule = module {
    single { StreamMessages(get()) }
    single { DeleteMessage(get()) }
}