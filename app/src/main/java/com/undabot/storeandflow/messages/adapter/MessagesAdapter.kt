package com.undabot.storeandflow.messages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.undabot.storeandflow.R
import com.undabot.storeandflow.util.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_message.view.*

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.QuestionItemHolder>() {
  var data: List<String> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }
  var listener: (String) -> Unit = {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemHolder =
    QuestionItemHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
    )

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: QuestionItemHolder, position: Int) =
    holder.bind(data[position], listener)

  class QuestionItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(category: String, listener: (String) -> Unit) {
      itemView.tv_question.text = category
      itemView.setSafeOnClickListener { listener(category) }
    }
  }
}