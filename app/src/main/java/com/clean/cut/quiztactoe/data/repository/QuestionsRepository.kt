package com.clean.cut.quiztactoe.data.repository

import com.clean.cut.quiztactoe.data.network.OpenTdbApiClient
import com.clean.cut.quiztactoe.data.resources.Result
import com.clean.cut.quiztactoe.data.resources.toModel
import com.clean.cut.quiztactoe.model.Question
import com.clean.cut.quiztactoe.util.safeApiCall
import com.clean.cut.quiztactoe.util.toResult

class QuestionsRepository(private val client: OpenTdbApiClient) {

  suspend fun getQuestions(): Result<List<Question>> {
    return safeApiCall { client.fetchQuestions().toResult().map { it.toModel() } }
  }
}