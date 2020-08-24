package com.undabot.storeandflow.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.fresh
import com.undabot.storeandflow.domain.model.Message
import com.undabot.storeandflow.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow

class MessagesRepositoryImpl(
  private val dataSource: MessagesDataSource
) : MessagesRepository {

  companion object {
    private const val key = "QUESTIONS"
  }

  private val store = StoreBuilder
    .fromNonFlow<String, List<Message>> {
      dataSource.fetchQuestions()
    }
    .build()

  override fun streamQuestions(refresh: Boolean): Flow<StoreResponse<List<Message>>> {
    return store.stream(StoreRequest.cached(key, refresh))
  }

  override suspend fun deleteMessage(id: String) {
    dataSource.deleteMessage(id)
    // refresh data set on delete
    store.fresh(key)
  }
}