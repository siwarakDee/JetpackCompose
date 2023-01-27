package com.example.jetpackcompose.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

object DispatcherModule {
    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

