package com.undabot.storeandflow.data.repository

import com.undabot.storeandflow.domain.model.Message
import kotlinx.coroutines.delay

class MessagesDataSource {
    var data = mutableListOf(
        Message("1", "Info", "This is an info message 1."),
        Message("2", "Info", "This is an info message 2."),
        Message("3", "Info", "This is an info message 3."),
        Message("4", "Info", "This is an info message 4."),
        Message("5", "Important", "This is an Important message 5."),
        Message("6", "Important", "This is an Important message 6."),
        Message("7", "Important", "This is an Important message 7."),
        Message("8", "Warning", "This is an Warning message 8."),
        Message("9", "Warning", "This is an Warning message 9."),
        Message("10", "Warning", "This is an Warning message 10.")
    )

    suspend fun fetchQuestions(): List<Message> {
        delay(1200)
        return data
    }

    suspend fun deleteMessage(id: String){
        delay(800)
        data = data.filter { it.id != id }.toMutableList()
    }

}