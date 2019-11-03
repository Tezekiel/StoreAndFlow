package com.clean.cut.quiztactoe.model.repository

import com.clean.cut.quiztactoe.model.network.OpenTdbApiClient
import com.clean.cut.quiztactoe.model.network.WebAccess
import com.clean.cut.quiztactoe.objects.Result

class QuizActivityRepository {
    var client: OpenTdbApiClient = WebAccess.quizApi

    suspend fun getQuestions(): Result {
        return client.quizQuery("api.php?amount=3&type=multiple")
    }
}