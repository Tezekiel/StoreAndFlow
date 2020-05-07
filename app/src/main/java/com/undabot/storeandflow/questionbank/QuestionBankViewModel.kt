package com.undabot.storeandflow.questionbank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undabot.storeandflow.data.resources.Result
import com.undabot.storeandflow.domain.interactors.GetQuestions
import com.undabot.storeandflow.domain.interactors.StreamQuestions
import com.undabot.storeandflow.questionbank.QuestionBankViewState.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class QuestionBankViewModel(
  private val streamQuestions: StreamQuestions,
  private val getQuestions: GetQuestions
) : ViewModel() {
  val state = MutableLiveData<QuestionBankViewState>()
  private val searchQuery = ConflatedBroadcastChannel("")

  init {
    viewModelScope.launch {
      searchQuery.asFlow()
        .flatMapLatest { streamQuestions() }
        .map { response ->
          when (response) {
            is Result.Success ->
              Content(response.data.filter { it.question.contains(searchQuery.value, true) })
            is Result.Error -> Error
          }
        }
        .onStart { emit(Loading) }
        .collect { state.value = it }
    }
  }

  fun retryClicked() {
    // send latest data from channel
    searchQuery.sendBlocking(searchQuery.value)
  }

  fun searchTermChanged(searchTerm: String?) {
    if (searchTerm != null) searchQuery.sendBlocking(searchTerm)
  }

  fun addMoreClicked() =
    viewModelScope.launch {
      when (getQuestions()) {
        is Result.Success -> searchQuery.sendBlocking(searchQuery.value)
        is Result.Error -> { }
      }
    }

}
