package com.undabot.storeandflow.domain.interactors

import com.dropbox.android.external.store4.StoreResponse
import com.undabot.storeandflow.domain.model.Message
import com.undabot.storeandflow.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow

class StreamMessages(
  private val repository: MessagesRepository
) {

  operator fun invoke(): Flow<StoreResponse<List<Message>>> {
    return repository.streamQuestions()
  }
}