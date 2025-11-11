package com.example.bankcard.utils

import android.app.Application
import com.example.bankcard.di.dataModule
import com.example.bankcard.di.interactorModule
import com.example.bankcard.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule, utilModule)
        }
    }
}