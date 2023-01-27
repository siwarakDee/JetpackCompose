package com.example.jetpackcompose.remote

import com.example.jetpackcompose.data.FetchModel
import retrofit2.http.GET

interface FetchApi {

    @GET("breeds/image/random")
    suspend fun fetch(): FetchModel

}