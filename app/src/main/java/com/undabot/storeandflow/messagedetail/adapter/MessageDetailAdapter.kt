package com.undabot.storeandflow.messagedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.undabot.storeandflow.R
import com.undabot.storeandflow.domain.model.Message
import com.undabot.storeandflow.util.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_message.view.tv_question
import kotlinx.android.synthetic.main.item_message_detail.view.*

class MessageDetailAdapter : RecyclerView.Adapter<MessageDetailAdapter.MessageItemHolder>() {
  var data: List<Message> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }
  var listener: (Message) -> Unit = {}

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemHolder =
    MessageItemHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_message_detail, parent, false)
    )

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: MessageItemHolder, position: Int) =
    holder.bind(data[position], listener)

  class MessageItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(message: Message, listener: (Message) -> Unit) {
      itemView.tv_question.text = message.text
      itemView.btn_delete.setSafeOnClickListener { listener(message) }
    }
  }
}