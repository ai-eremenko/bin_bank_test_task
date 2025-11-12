package com.example.bankcard.di

import com.example.bankcard.presentation.binsearch.BinSearchViewModel
import com.example.bankcard.presentation.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        BinSearchViewModel(
            interactor = get()
        )
    }

    viewModel {
        HistoryViewModel(
            interactor = get()
        )
    }
}