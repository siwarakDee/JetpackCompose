package com.example.jetpackcompose.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    fun createFetchAPI(): FetchApi {
       return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(FetchApi::class.java)
    }
}