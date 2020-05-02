package com.clean.cut.quiztactoe.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.model.Question
import com.clean.cut.quiztactoe.util.setSafeOnClickListener
import com.google.android.material.button.MaterialButton

class AnswersFactory {
  fun into(
    parent: ViewGroup,
    question: Question,
    listener: (View, Boolean) -> Unit = {_, _ ->}
  ) {
    //clear all views
    parent.removeAllViews()

    val allAnswers = (question.incorrectAnswers + question.correctAnswer).shuffled()
    val inflater = LayoutInflater.from(parent.context)

    //create question view
    val questionView = inflater.inflate(R.layout.question_item_layout, parent, false) as TextView
    questionView.text = question.question
    parent.addView(questionView)

    //create answers
    allAnswers.forEach { answer ->
      //inflate message
      val view = inflater.inflate(R.layout.answer_item_layout, parent, false)

      //on click message body
      (view as MaterialButton).apply {
        text = answer
        setSafeOnClickListener {
          val correct = isCorrect(answer, question.correctAnswer)
          listener(view, correct)
        }
      }

      //add to parent
      parent.addView(view)
    }
  }

  private fun isCorrect(answer: String, correctAnswer: String): Boolean {
    return answer == correctAnswer
  }
}
