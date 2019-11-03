package com.clean.cut.quiztactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.model.repository.QuizActivityRepository
import com.clean.cut.quiztactoe.objects.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val repository: QuizActivityRepository =
            QuizActivityRepository()

        fun getSearchData() {
            lifecycleScope.launch {
                val questionsResult = withContext(Dispatchers.IO) {
                    repository.getQuestions()
                }
                Log.v("primjer", questionsResult.results[0].question)

            }
        }

        getSearchData()
    }
}
