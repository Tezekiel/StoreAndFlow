package com.undabot.storeandflow.util

import com.dropbox.android.external.store4.StoreResponse
import com.undabot.storeandflow.data.resources.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map

fun <T> Flow<StoreResponse<T>>.toResultFlow(): Flow<Result<T>> {
  return this
    .filterNot { it is StoreResponse.Loading }
    .map {
      when (it) {
        is StoreResponse.Data -> Result.Success(it.value)
        is StoreResponse.Error -> Result.Error(it.error)
        is StoreResponse.Loading -> throw IllegalStateException()
      }
    }
}