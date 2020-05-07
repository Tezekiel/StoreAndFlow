package com.undabot.storeandflow.questionbank

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.storeandflow.R
import com.undabot.storeandflow.questionbank.adapter.QuestionBankAdapter
import kotlinx.android.synthetic.main.activity_login_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionBankActivity : AppCompatActivity() {
  private val viewModel by viewModel<QuestionBankViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login_activity)

    // set up listener
    btn_error_retry.setOnClickListener { viewModel.retryClicked() }
    fab_more_questions.setOnClickListener { viewModel.addMoreClicked() }

    sv.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
      androidx.appcompat.widget.SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0: String?): Boolean = false
      override fun onQueryTextChange(p0: String?): Boolean {
        viewModel.searchTermChanged(p0)
        return false
      }
    })

    // set up rv
    rv_question_bank.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      adapter = QuestionBankAdapter()
    }

    // observe viewModel state
    viewModel.state.observe(this, Observer { render(it) })
  }

  private fun render(state: QuestionBankViewState) {
    when(state){
      QuestionBankViewState.Loading -> {
        pb_loading.isVisible = true
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_question_bank.isVisible = false
        fab_more_questions.isVisible = false
        sv.isVisible = false
      }
      QuestionBankViewState.Error -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = true
        iv_error.isVisible = true
        rv_question_bank.isVisible = false
        fab_more_questions.isVisible = false
        sv.isVisible = false
      }
      is QuestionBankViewState.Content -> {
        pb_loading.isVisible = false
        btn_error_retry.isVisible = false
        iv_error.isVisible = false
        rv_question_bank.isVisible = true
        fab_more_questions.isVisible = true
        sv.isVisible = true
        with(state){
          (rv_question_bank.adapter as QuestionBankAdapter).data = content
        }
      }
    }
  }
}
