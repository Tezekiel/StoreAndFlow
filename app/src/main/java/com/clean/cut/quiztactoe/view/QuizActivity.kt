package com.clean.cut.quiztactoe.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.databinding.ActivityQuizBinding
import com.clean.cut.quiztactoe.viewmodel.QuizViewModel

class QuizActivity : AppCompatActivity() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initialize()
    }

    private fun initialize() {
        //boilerplate
        val binding: ActivityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        //architecture stuff
        viewModel.init(getDataFromIntent())
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        //start Quiz
        viewModel.getSearchData()
    }

    private fun getDataFromIntent(): String {
        return intent.getStringExtra("playerName")
    }
}
