package com.example.jetpackcompose.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.runOn(coroutineScope: CoroutineScope, onCollect: (T) -> Unit) {
	coroutineScope.launch {
		collect { onCollect.invoke(it) }
	}
}