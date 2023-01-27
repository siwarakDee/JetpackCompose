package com.example.jetpackcompose.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.FetchModel
import com.example.jetpackcompose.domain.UseCase
import com.example.jetpackcompose.extension.runOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MainViewModel(private val useCase:UseCase<Unit,FetchModel>) :ViewModel() {

    private val _dogImage  = MutableLiveData<String>()
    val dogImage = _dogImage

    private val _loading  = MutableLiveData<Boolean>(false)
    val loading = _loading

    fun fetchDog(){
        useCase.invoke(Unit)
            .onStart {
               _loading.value = true
            }
            .onCompletion {
               _loading.value = false
            }
            .runOn(viewModelScope) { result ->
                result.onSuccess {
                    _dogImage.value = it.message
                }.onFailure { throwable ->
                    throwable
                }
            }
    }

}