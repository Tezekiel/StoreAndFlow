package com.undabot.storeandflow.data.resources

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.undabot.storeandflow.data.network.Resource
import com.undabot.storeandflow.domain.model.Question

@JsonClass(generateAdapter = true)
data class QuestionsResource(
  @Json(name = "response_code")
  override val responseCode: Int,
  @Json(name = "results")
  val results: List<Attributes>
) : Resource() {

  @JsonClass(generateAdapter = true)
  data class Attributes(
    @Json(name = "category")
    var category: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "difficulty")
    val difficulty: String,
    @Json(name = "question")
    var question: String,
    @Json(name = "correct_answer")
    var correctAnswer: String,
    @Json(name = "incorrect_answers")
    var incorrectAnswers: List<String>
  )
}

fun QuestionsResource.toModel(): List<Question> {
  val listOfQuestions = mutableListOf<Question>()
  this.results.forEach {
    with(it) {
      listOfQuestions.add(
        Question(
          category = category.replaceStrings(),
          type = type.replaceStrings(),
          difficulty = difficulty.replaceStrings(),
          question = question.replaceStrings(),
          correctAnswer = correctAnswer.replaceStrings(),
          incorrectAnswers = incorrectAnswers.map { text -> text.replaceStrings() }
        )
      )
    }
  }
  return listOfQuestions
}

private fun String.replaceStrings() : String{
  return this
    .replace("&quot;", "\"")
    .replace("&#039;", "'")
    .replace("&amp;", "&")
}

