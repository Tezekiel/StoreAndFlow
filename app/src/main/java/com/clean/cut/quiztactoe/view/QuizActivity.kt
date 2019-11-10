package com.clean.cut.quiztactoe.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.databinding.ActivityQuizBinding
import com.clean.cut.quiztactoe.viewmodel.QuizViewModel

class QuizActivity : AppCompatActivity() {
    private lateinit var viewModel: QuizViewModel
    private var clickedViewId = 0
    var isItCorrect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initialize()
    }

    private fun initialize() {
        //boilerplate
        val binding: ActivityQuizBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        //get player name from last activity
        viewModel.init(getDataFromIntent())

        //architecture stuff
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        //start Quiz
        viewModel.getSearchData()

        //observers
        viewModel.clickedAnswer.observe(this, Observer {
            clickedViewId = it
        })
        viewModel.isItCorrect.observe(this, Observer {
            isItCorrect = it
            colorTheClickedAnswer()
        })
        viewModel.backToGame.observe(this, Observer {
            if(it){
                val returnIntent = Intent(this, LocalGameActivity::class.java)
                returnIntent.putExtra("result", isItCorrect)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        })
    }

    private fun colorTheClickedAnswer() {
        val clickedView = findViewById<Button>(clickedViewId)
        if (isItCorrect){
            clickedView.backgroundTintList = ContextCompat.getColorStateList(this@QuizActivity, R.color.green)
        }else{
            clickedView.backgroundTintList = ContextCompat.getColorStateList(this@QuizActivity, R.color.red)
        }
    }

    private fun getDataFromIntent(): String {
        return intent.getStringExtra("playerName")
    }
}
