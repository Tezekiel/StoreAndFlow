package com.undabot.storeandflow.data.network

import com.undabot.storeandflow.data.resources.QuestionsResource
import retrofit2.Response
import retrofit2.http.GET

interface OpenTdbApiClient {
    @GET("api.php?amount=3&type=multiple")
    suspend fun fetchQuestions(): Response<QuestionsResource>

}