package com.example.jetpackcompose.domain

import com.example.jetpackcompose.data.FetchModel
import com.example.jetpackcompose.remote.FetchApi
import com.example.jetpackcompose.remote.FetchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FetchDataUseCase(
    private val repository: FetchRepository
) : UseCase<Unit, FetchModel>(Dispatchers.IO) {
    override fun execute(request: Unit): Flow<FetchModel> {
       return flow {
            emit(repository.fetch())
        }
    }
}