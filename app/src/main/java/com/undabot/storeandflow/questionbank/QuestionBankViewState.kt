package com.undabot.storeandflow.questionbank

import com.undabot.storeandflow.domain.model.Question

sealed class QuestionBankViewState {
  object Loading : QuestionBankViewState()
  object Error : QuestionBankViewState()
  data class Content(val content: List<Question>) : QuestionBankViewState()
}