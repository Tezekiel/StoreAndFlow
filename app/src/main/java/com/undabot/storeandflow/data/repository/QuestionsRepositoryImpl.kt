package com.undabot.storeandflow.data.repository

import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.get
import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.data.resources.resultFrom
import com.undabot.storeandflow.data.resources.toModel
import com.undabot.storeandflow.domain.model.Question
import com.undabot.storeandflow.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class QuestionsRepositoryImpl(
  private val dataSource: QuestionsDataSource
) : QuestionsRepository {

  companion object {
    val keys = mutableListOf(1)
  }

  private val store = StoreBuilder
    .fromNonFlow<Int, List<Question>> {
      dataSource
        .fetchQuestions()  // Result<Resource>
        .getOrThrow() // if Result.Success -> data
        .toModel() // map to domain model
    }
    .build()

  // simple example not hooked up
  // returns Flow<StoreResponse<Output>> from cache
  fun streamQuestionsVanila(): Flow<StoreResponse<List<Question>>> {
    return store.stream(StoreRequest.cached(keys.first(), false))
  }

  // stream all data from cache
  override suspend fun streamQuestions(): Flow<Result<List<Question>>> {
    return flowOf(resultFrom {
      keys
        .map { store.get(it) }
        .flatten()
    })
  }

  override suspend fun getQuestions(): Result<List<Question>> {
    keys.add(keys.last() + 1)
    return resultFrom { store.get(keys.last()) }
  }

}