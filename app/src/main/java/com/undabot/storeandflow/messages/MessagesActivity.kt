package com.undabot.storeandflow.messages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.storeandflow.R
import com.undabot.storeandflow.messagedetail.MessageDetailActivity
import com.undabot.storeandflow.messages.adapter.MessagesAdapter
import kotlinx.android.synthetic.main.activity_messages.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessagesActivity : AppCompatActivity() {
  private val viewModel by viewModel<MessagesViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_messages)

    // set up rv
    rv_messages.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = MessagesAdapter()
    }

    // observe viewModel state
    viewModel.state.observe(this, Observer { render(it) })
  }

  private fun render(state: MessagesViewState) {
    when (state) {
      MessagesViewState.Loading -> {
        pb_loading.isVisible = true
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_messages.isVisible = false
      }
      MessagesViewState.Error -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = true
        iv_error.isVisible = true
        rv_messages.isVisible = false
      }
      is MessagesViewState.Content -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_messages.isVisible = true
        with(state) {
          num_msg_tv.text = this.content.count().toString()
          (rv_messages.adapter as MessagesAdapter).data = content.distinct()
          (rv_messages.adapter as MessagesAdapter).listener =
            { startActivity(MessageDetailActivity.with(this@MessagesActivity, it))}
        }
      }
    }
  }
}
