package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.FetchModel
import com.example.jetpackcompose.domain.FetchDataUseCase
import com.example.jetpackcompose.domain.UseCase
import com.example.jetpackcompose.presentation.MainViewModel
import com.example.jetpackcompose.remote.FetchApi
import com.example.jetpackcompose.remote.FetchRepoImp
import com.example.jetpackcompose.remote.FetchRepository
import com.example.jetpackcompose.remote.RetrofitFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val serviceModule = module {
    single<FetchApi>{ RetrofitFactory().createFetchAPI() }
}

val repoModule = module {
    factory<FetchRepository> { FetchRepoImp(get()) }
}

val useCaseModule = module {
    factory<UseCase<Unit,FetchModel>> { FetchDataUseCase(get()) }
}

