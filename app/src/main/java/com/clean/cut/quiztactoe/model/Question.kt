package com.clean.cut.quiztactoe.model

data class Question (
  var category: String,
  val type: String,
  val difficulty: String,
  var question: String,
  var correctAnswer: String,
  var incorrectAnswers: List<String>
)
