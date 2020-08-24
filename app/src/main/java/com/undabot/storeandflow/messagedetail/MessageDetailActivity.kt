package com.undabot.storeandflow.messagedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.storeandflow.R
import com.undabot.storeandflow.messagedetail.adapter.MessageDetailAdapter
import kotlinx.android.synthetic.main.activity_messages.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageDetailActivity : AppCompatActivity() {

  companion object {
  private const val TYPE = "1234"
  fun with(context: Context, type: String ): Intent =
    Intent(context, MessageDetailActivity::class.java).putExtra(TYPE, type)
}

  private val viewModel by viewModel<MessageDetailViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_messages)

    viewModel.typeSelected(intent.getStringExtra(TYPE))

    // set up rv
    rv_messages.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = MessageDetailAdapter()
    }

    // observe viewModel state
    viewModel.state.observe(this, Observer { render(it) })
  }

  private fun render(state: MessageDetailViewState) {
    when (state) {
      MessageDetailViewState.Loading -> {
        pb_loading.isVisible = true
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_messages.isVisible = false
      }
      MessageDetailViewState.Error -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = true
        iv_error.isVisible = true
        rv_messages.isVisible = false
      }
      is MessageDetailViewState.Content -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_messages.isVisible = true
        with(state) {
          num_msg_tv.text = this.content.count().toString()
          (rv_messages.adapter as MessageDetailAdapter).data = content
          (rv_messages.adapter as MessageDetailAdapter).listener = { viewModel.delete(it.id) }
        }
      }
    }
  }
}
