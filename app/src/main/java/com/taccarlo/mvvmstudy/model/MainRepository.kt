package com.taccarlo.mvvmstudy.model

import com.taccarlo.mvvmstudy.service.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}