package com.taccarlo.mvvmstudy.api

import com.taccarlo.mvvmstudy.model.LinkedinRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("repos/{owner}/{name}/stargazers")
    fun getAllLinkedinRepos(
        @Path("owner") input1: String,
        @Path("name") input2: String
    ): Call<List<LinkedinRepository>>

    /**
     * Retrofit service instance using the retrofit
     * Using Singleton pattern
     * another way to implement Singleton pattern in Kotlin is:
     * object ExampleSingleton {
     * fun exampleMethod() {
     *     // ...
     *    }
     * }
     */
    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

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