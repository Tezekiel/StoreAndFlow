package com.undabot.storeandflow.domain.interactors

import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.domain.model.Question
import com.undabot.storeandflow.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow

class StreamQuestions(
  private val repository: QuestionsRepository
) {

  suspend operator fun invoke(): Flow<Result<List<Question>>> {
    return repository.streamQuestions()
  }
}