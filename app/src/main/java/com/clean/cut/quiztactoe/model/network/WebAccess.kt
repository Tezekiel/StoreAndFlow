package com.clean.cut.quiztactoe.model.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebAccess {
    val quizApi : OpenTdbApiClient by lazy {
        val retrofit = Retrofit.Builder()

            .baseUrl("https://opentdb.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return@lazy retrofit.create(OpenTdbApiClient::class.java)
    }
}