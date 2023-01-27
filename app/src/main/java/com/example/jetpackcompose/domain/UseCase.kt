package com.example.jetpackcompose.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase <P, R>(private val coroutineDispatcher: CoroutineDispatcher) :
    FlowResultInterActor<P, R> {
    override fun invoke(request: P): Flow<Result<R>> = execute(request)
        .map { Result.success(it) }
        .catch { e ->
            emit(Result.failure(e))
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(request: P): Flow<R>
}

interface FlowResultInterActor<P, R> {
    operator fun invoke(request: P): Flow<Result<R>>
}