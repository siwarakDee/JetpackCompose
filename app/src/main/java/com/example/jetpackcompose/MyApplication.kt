package com.example.jetpackcompose

import android.app.Application
import com.example.jetpackcompose.di.repoModule
import com.example.jetpackcompose.di.serviceModule
import com.example.jetpackcompose.di.useCaseModule
import com.example.jetpackcompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MyApplication)
            val allModule = arrayListOf(
                serviceModule,
                repoModule,
                useCaseModule,
                viewModelModule
            )
            modules(allModule)
        }
    }
}