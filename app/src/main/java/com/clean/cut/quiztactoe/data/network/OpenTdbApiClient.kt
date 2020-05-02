package com.clean.cut.quiztactoe.data.network

import com.clean.cut.quiztactoe.data.resources.QuestionsResource
import retrofit2.Response
import retrofit2.http.GET

interface OpenTdbApiClient {
    @GET("api.php?amount=3&type=multiple")
    suspend fun fetchQuestions(): Response<QuestionsResource>

}