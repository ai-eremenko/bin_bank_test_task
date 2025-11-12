package com.example.bankcard.di

import androidx.room.Room
import com.example.bankcard.data.db.AppDatabase
import com.example.bankcard.data.network.BinApiService
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    factory { Gson() }

    single<BinApiService> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApiService::class.java)
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "bin_history.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().binInfoDao()
    }
}