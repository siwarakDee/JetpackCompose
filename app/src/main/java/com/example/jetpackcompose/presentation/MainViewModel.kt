package com.example.jetpackcompose.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.FetchModel
import com.example.jetpackcompose.domain.UseCase
import com.example.jetpackcompose.extension.runOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MainViewModel(val useCase:UseCase<Unit,FetchModel>) :ViewModel() {

    private val _dogImage  = MutableLiveData<String>()
    val dogImage = _dogImage

    fun fetchDog(){
        useCase.invoke(Unit)
            .onStart {
               // showLoading()
            }
            .onCompletion {
               // hideLoading()
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