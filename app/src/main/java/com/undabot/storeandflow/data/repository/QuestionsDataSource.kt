package com.undabot.storeandflow.data.repository

import com.undabot.storeandflow.data.network.OpenTdbApiClient
import com.undabot.storeandflow.data.resources.QuestionsResource
import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.util.safeApiCall
import com.undabot.storeandflow.util.toResult

class QuestionsDataSource(private val client: OpenTdbApiClient) {

    suspend fun fetchQuestions(): Result<QuestionsResource> {
        return safeApiCall { client.fetchQuestions().toResult() }
    }

}