package com.example.bankcard.di

import com.example.bankcard.data.binsearch.BinSearchRepositoryImpl
import com.example.bankcard.domain.binsearch.BinSearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BinSearchRepository> { BinSearchRepositoryImpl(get(), get(),
        get()) }
}