package com.undabot.storeandflow.domain.interactors

import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.domain.model.Question
import com.undabot.storeandflow.domain.repository.QuestionsRepository

class GetQuestions(
  private val repository: QuestionsRepository
) {

  suspend operator fun invoke(): Result<List<Question>> {
    return repository.getQuestions()
  }
}