package com.undabot.storeandflow.messagedetail

import com.undabot.storeandflow.domain.model.Message

sealed class MessageDetailViewState {
  object Loading : MessageDetailViewState()
  object Error : MessageDetailViewState()
  data class Content(val content: List<Message>) : MessageDetailViewState()
}