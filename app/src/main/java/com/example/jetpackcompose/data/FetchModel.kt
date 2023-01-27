package com.example.jetpackcompose.data

import com.google.gson.annotations.SerializedName

data class FetchModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)