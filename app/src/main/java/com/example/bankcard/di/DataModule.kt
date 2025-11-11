package com.example.bankcard.di

import android.content.Context
import androidx.room.Room
import com.example.bankcard.data.db.AppDatabase
import com.example.bankcard.data.network.BinApiService
import com.example.bankcard.data.network.NetworkClient
import com.example.bankcard.data.network.RetrofitClient
import com.example.bankcard.utils.NetworkManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        androidContext()
            .getSharedPreferences(EXAMPLE_PREFERENCES, Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single {
        NetworkManager(androidContext())
    }

    single<NetworkClient> {
        RetrofitClient(get(), get())
    }

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
        Storage(get(), get())
    }

    single {
        get<AppDatabase>().binInfoDao()
    }
}