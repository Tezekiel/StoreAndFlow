package com.clean.cut.quiztactoe.model.network

import com.clean.cut.quiztactoe.objects.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTdbApiClient {
    @GET("api.php?amount=3&type=multiple")
    suspend fun quizQuery(@Query(value = "q") query: String): Result

}