package com.example.bankcard

import android.app.Application
import com.example.bankcard.data.dataModule
import com.example.bankcard.domain.interactorModule
import com.example.bankcard.feature.binsearch.binSearchModule
import com.example.bankcard.feature.history.historyModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                interactorModule,
                binSearchModule,
                historyModule
            )
        }
    }
}