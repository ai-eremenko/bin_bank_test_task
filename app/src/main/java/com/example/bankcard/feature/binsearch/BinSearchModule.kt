package com.example.bankcard.feature.binsearch

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val binSearchModule = module {
    viewModel {
        BinSearchViewModel(
            interactor = get()
        )
    }
}