package com.undabot.storeandflow.messages

sealed class MessagesViewState {
  object Loading : MessagesViewState()
  object Error : MessagesViewState()
  data class Content(val content: List<String>) : MessagesViewState()
}