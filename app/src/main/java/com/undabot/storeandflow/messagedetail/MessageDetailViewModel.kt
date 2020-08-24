package com.undabot.storeandflow.messagedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.StoreResponse.*
import com.undabot.storeandflow.domain.interactors.DeleteMessage
import com.undabot.storeandflow.domain.interactors.StreamMessages
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MessageDetailViewModel(
  streamQuestions: StreamMessages,
  val deleteMessage: DeleteMessage
) : ViewModel() {

  val state = MutableLiveData<MessageDetailViewState>()

  private val event = ConflatedBroadcastChannel<String>()

  init {
    event
      .asFlow()
      .flatMapLatest { type ->
        streamQuestions()
          .map { response ->
            when (response) {
              is Loading -> state.value = MessageDetailViewState.Loading
              is Data -> state.value = MessageDetailViewState.Content(response.value.filter { it.type == type })
              is Error -> state.value = MessageDetailViewState.Error
            }
          }
      }
      .launchIn(viewModelScope)
  }

  fun typeSelected(type: String) = event.offer(type)

  fun delete(id: String) {
    viewModelScope.launch { deleteMessage(id) }
  }

}

