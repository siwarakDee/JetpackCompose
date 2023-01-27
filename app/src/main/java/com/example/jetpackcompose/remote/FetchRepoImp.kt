package com.example.jetpackcompose.remote

import com.example.jetpackcompose.data.FetchModel

class FetchRepoImp(private val api: FetchApi) :FetchRepository {

    override suspend fun fetch(): FetchModel {
        return  api.fetch()
    }
}