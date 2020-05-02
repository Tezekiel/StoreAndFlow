package com.clean.cut.quiztactoe.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.model.Question
import com.clean.cut.quiztactoe.util.setSafeOnClickListener
import com.google.android.material.button.MaterialButton

class CategoryItemFactory {
  @SuppressLint("SetTextI18n")
  fun into(parent: ViewGroup,
           questions: List<Question>,
           listener: (Question) -> Unit = {}
  ) {
    //clear all views
    parent.removeAllViews()

    val inflater = LayoutInflater.from(parent.context)

    questions.forEach { question ->
      //inflate message
      val view = inflater.inflate(R.layout.category_item_layout, parent, false)

      //on click message body
      (view as MaterialButton).apply {
        text = "${question.category} (${question.difficulty})"
        setSafeOnClickListener { listener(question) }
      }

      //add to parent
      parent.addView(view)
    }
  }
}