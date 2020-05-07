package com.undabot.storeandflow.questionbank.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.undabot.storeandflow.R
import com.undabot.storeandflow.domain.model.Question
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionBankAdapter: RecyclerView.Adapter<QuestionBankAdapter.QuestionItemHolder>() {
  var data: List<Question> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemHolder =
    QuestionItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false))

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: QuestionItemHolder, position: Int) = holder.bind(data[position])

  class QuestionItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(question: Question) {
      itemView.tv_question.text = question.question
    }
  }
}