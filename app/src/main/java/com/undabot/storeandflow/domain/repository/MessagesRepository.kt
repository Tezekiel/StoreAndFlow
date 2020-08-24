package com.undabot.storeandflow.domain.repository

import com.dropbox.android.external.store4.StoreResponse
import com.undabot.storeandflow.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

  fun streamQuestions(refresh: Boolean = false): Flow<StoreResponse<List<Message>>>

  suspend fun deleteMessage(id: String)
}