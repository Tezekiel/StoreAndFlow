package com.clean.cut.quiztactoe.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.cut.quiztactoe.model.repository.QuizActivityRepository
import com.clean.cut.quiztactoe.objects.Question
import com.clean.cut.quiztactoe.objects.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuizViewModel : ViewModel() {
    private lateinit var correctAnswer: String

    private val repository: QuizActivityRepository = QuizActivityRepository()
    val isLoading = MutableLiveData<Boolean>(true)
    val hideFirst = MutableLiveData<Boolean>(false)
    val playerName = MutableLiveData<String>()
    val category1 = MutableLiveData<Question>()
    val category2 = MutableLiveData<Question>()
    val category3 = MutableLiveData<Question>()
    val question = MutableLiveData<String>()
    val answers = MutableLiveData<List<String>>()
    val isItCorrect = MutableLiveData<Boolean>()
    val clickedAnswer = MutableLiveData<Int>()

    val questionAnswered = MutableLiveData<Boolean>()
    val counter = MutableLiveData(5)
    val backToGame = MutableLiveData<Boolean>()


    fun init(playerName: String) {
        this.playerName.value = playerName
    }

    fun getSearchData() {
        isLoading.value = true
        viewModelScope.launch {
            val questionsResult = withContext(Dispatchers.IO) {
                repository.getQuestions()
            }

            replaceStrings(questionsResult)

            isLoading.value = false
            category1.value = questionsResult.results[0]
            category2.value = questionsResult.results[1]
            category3.value = questionsResult.results[2]
        }
    }

    fun onCategorySelected(listPosition: Int) {
        hideFirst.value = true
        lateinit var chosenCategory: MutableLiveData<Question>
        when (listPosition) {
            0 -> chosenCategory = category1
            1 -> chosenCategory = category2
            2 -> chosenCategory = category3
        }
        populateQuiz(chosenCategory)
    }

    private fun populateQuiz(chosenCategory: MutableLiveData<Question>) {
        question.value = chosenCategory.value?.question
        correctAnswer = chosenCategory.value?.correctAnswer.toString()
        val allAnswers = mutableListOf<String>()
        allAnswers.addAll(chosenCategory.value?.incorrectAnswers!!)
        allAnswers.add(chosenCategory.value?.correctAnswer!!)
        allAnswers.shuffle()
        answers.value = allAnswers
    }

    fun isItCorrect(answer: String, view: View) {
        clickedAnswer.value = view.id
        isItCorrect.value = answer == correctAnswer

        questionAnswered.value = true
        showContinueGameBtn()
    }

    private fun showContinueGameBtn() {
        val timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                counter.value = counter.value?.minus(1)
            }

            override fun onFinish() {
                backToGame()
            }
        }
        timer.start()


    }

    fun backToGame() {
        backToGame.value = true
    }

    private fun replaceStrings(questionsResult: Result) {
        //categories
        questionsResult.results.forEach { it.category = it.category.replace("&quot;", "\"") }
        questionsResult.results.forEach { it.category = it.category.replace("&#039;", "'") }

        //questions
        questionsResult.results.forEach { it.question = it.question.replace("&quot;", "\"") }
        questionsResult.results.forEach { it.question = it.question.replace("&#039;", "'") }

        //correctAnswers
        questionsResult.results.forEach {
            it.correctAnswer = it.correctAnswer.replace("&quot;", "\"")
        }
        questionsResult.results.forEach {
            it.correctAnswer = it.correctAnswer.replace("&#039;", "'")
        }
        //wrongAnswers
        questionsResult.results.forEach { it1 ->
            it1.incorrectAnswers = it1.incorrectAnswers.map { it.replace("&quot;", "\"") }
        }
        questionsResult.results.forEach { it1 ->
            it1.incorrectAnswers = it1.incorrectAnswers.map { it.replace("&#039;;", "\"") }
        }
    }


}