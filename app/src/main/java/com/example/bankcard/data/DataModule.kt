package com.example.bankcard.data

import android.app.Application
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bankcard.data.binsearch.BinSearchRepositoryImpl
import com.example.bankcard.data.db.AppDatabase
import com.example.bankcard.data.history.HistoryRepositoryImpl
import com.example.bankcard.data.mapper.BinInfoMapper
import com.example.bankcard.data.network.BinApiService
import com.example.bankcard.domain.binsearch.BinSearchRepository
import com.example.bankcard.domain.history.HistoryRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    factory { Gson() }
    single { provideOkHttpClient(androidApplication()) }
    single { provideRetrofit(get()) }
    single { provideBinApiService(get()) }
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "bin_history.db"
        )
            .build()
    }
    single {
        get<AppDatabase>().binInfoDao()
    }
    single<BinSearchRepository> {
        BinSearchRepositoryImpl(
            get(), get(),
            get()
        )
    }
    single<HistoryRepository> {
        HistoryRepositoryImpl(
            get()
        )
    }
    single { BinInfoMapper }
}

private fun provideOkHttpClient(application: Application): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor(application))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

private fun provideBinApiService(retrofit: Retrofit): BinApiService {
    return retrofit.create(BinApiService::class.java)
}