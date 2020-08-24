package com.undabot.storeandflow.messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.StoreResponse.*
import com.undabot.storeandflow.domain.interactors.StreamMessages
import com.undabot.storeandflow.domain.model.Message
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class MessagesViewModel(
  streamQuestions: StreamMessages
) : ViewModel() {
  val state = MutableLiveData<MessagesViewState>()

  init {
    streamQuestions()
      .map { response ->
        when (response) {
          is Loading -> state.value = MessagesViewState.Loading
          is Data -> state.value = MessagesViewState.Content(response.value.toCategory())
          is Error -> state.value = MessagesViewState.Error
        }
      }
      .launchIn(viewModelScope)
  }
}

private fun List<Message>.toCategory(): List<String> {
  return this.map { it.type }
}
