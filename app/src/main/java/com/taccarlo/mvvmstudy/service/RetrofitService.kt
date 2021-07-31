package com.taccarlo.mvvmstudy.service

import com.taccarlo.mvvmstudy.model.LinkedinRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("repos/immuni-app/immuni/stargazers")
    fun getAllMovies(): Call<List<LinkedinRepository>>

    /**
     * Retrofit service instance using the retrofit
     */
    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}