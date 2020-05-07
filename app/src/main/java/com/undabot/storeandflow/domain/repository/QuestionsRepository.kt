package com.undabot.storeandflow.domain.repository

import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {

  suspend fun streamQuestions(): Flow<Result<List<Question>>>

  suspend fun getQuestions(): Result<List<Question>>
}