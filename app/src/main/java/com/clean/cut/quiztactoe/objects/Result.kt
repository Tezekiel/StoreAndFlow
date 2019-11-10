package com.clean.cut.quiztactoe.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result (
    @Json(name = "response_code")
    val responseCode: Int,
    @Json(name = "results")
    val results: List<Question>
)

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "category")
    var category: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "difficulty")
    val difficulty: String,
    @Json(name = "question")
    var question: String,
    @Json(name = "correct_answer")
    var correctAnswer:String,
    @Json(name = "incorrect_answers")
    var incorrectAnswers:List<String>
)

