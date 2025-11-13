package com.example.bankcard.domain

import com.example.bankcard.domain.binsearch.BinSearchInteractor
import com.example.bankcard.domain.binsearch.BinSearchInteractorImpl
import com.example.bankcard.domain.history.HistoryInteractor
import com.example.bankcard.domain.history.HistoryInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<BinSearchInteractor> { BinSearchInteractorImpl(get()) }
    single<HistoryInteractor> { HistoryInteractorImpl(get()) }
}