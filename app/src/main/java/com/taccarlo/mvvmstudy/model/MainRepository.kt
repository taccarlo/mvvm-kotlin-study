package com.taccarlo.mvvmstudy.model

import com.taccarlo.mvvmstudy.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllLinkedinRepos(input1:String, input2:String) = retrofitService.getAllLinkedinRepos(input1, input2)
}