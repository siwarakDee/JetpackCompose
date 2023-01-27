package com.example.jetpackcompose.remote

import com.example.jetpackcompose.data.FetchModel

interface FetchRepository {
    suspend fun fetch() : FetchModel
}