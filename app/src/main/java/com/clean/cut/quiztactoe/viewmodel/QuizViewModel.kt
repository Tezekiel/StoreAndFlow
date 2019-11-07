package com.clean.cut.quiztactoe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.cut.quiztactoe.model.repository.QuizActivityRepository
import com.clean.cut.quiztactoe.objects.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuizViewModel: ViewModel() {
    private val repository: QuizActivityRepository = QuizActivityRepository()
    val isLoading = MutableLiveData<Boolean>(true)
    val hideFirst = MutableLiveData<Boolean>(false)
    val playerName = MutableLiveData<String>()
    val category1 = MutableLiveData<Question>()
    val category2 = MutableLiveData<Question>()
    val category3 = MutableLiveData<Question>()


    fun init(playerName: String) {
        this.playerName.value = playerName
    }

    fun getSearchData() {
        isLoading.value = true
        viewModelScope.launch {
            val questionsResult = withContext(Dispatchers.IO) {
                repository.getQuestions()
            }

            isLoading.value = false
            category1.value = questionsResult.results[0]
            category2.value = questionsResult.results[1]
            category3.value = questionsResult.results[2]
        }
    }

    fun onCategorySelected(listPosition: Int){
        hideFirst.value = true
        when(listPosition){
            0-> Log.v("primjer", "listpos $listPosition")
            1-> Log.v("primjer", "listpos $listPosition")
            2-> Log.v("primjer", "listpos $listPosition")
        }
    }


}