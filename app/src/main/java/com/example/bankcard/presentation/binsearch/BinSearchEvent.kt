package com.example.bankcard.presentation.binsearch

sealed interface BinSearchEvent {
    data class SearchQueryChanged(val query: String) : BinSearchEvent
    object SearchBinInfo : BinSearchEvent
    object ClearError : BinSearchEvent
    data class ShowBottomSheet(val show: Boolean) : BinSearchEvent
}