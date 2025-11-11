package com.example.bankcard.di

import com.example.bankcard.domain.binsearch.BinSearchInteractor
import com.example.bankcard.domain.binsearch.BinSearchInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<BinSearchInteractor> { BinSearchInteractorImpl(get()) }
}