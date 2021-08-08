package com.taccarlo.mvvmstudy.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taccarlo.mvvmstudy.model.LinkedinRepository
import com.taccarlo.mvvmstudy.model.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val linkedinRepoList = MutableLiveData<List<LinkedinRepository>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllLinkedinRepos(input1: String, input2: String) {
        if (input1?.isEmpty() || input2?.isEmpty())
            return
        val in1 = input1.lowercase().trim()
        val in2 = input2.lowercase().trim()
        val response = repository.getAllLinkedinRepos(in1, in2)
        response.enqueue(object : Callback<List<LinkedinRepository>> {
            override fun onResponse(
                call: Call<List<LinkedinRepository>>,
                response: Response<List<LinkedinRepository>>
            ) {
                linkedinRepoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<LinkedinRepository>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}