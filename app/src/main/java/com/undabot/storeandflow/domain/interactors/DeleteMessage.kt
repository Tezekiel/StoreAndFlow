package com.undabot.storeandflow.domain.interactors

import com.undabot.storeandflow.domain.repository.MessagesRepository

class DeleteMessage(
  private val repository: MessagesRepository
) {

  suspend operator fun invoke(id: String) {
    return repository.deleteMessage(id)
  }
}