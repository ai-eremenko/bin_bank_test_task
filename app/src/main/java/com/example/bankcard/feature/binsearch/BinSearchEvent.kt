package com.example.bankcard.feature.binsearch

sealed interface BinSearchEvent {
    data class SearchQueryChanged(val query: String) : BinSearchEvent
    data object SearchButtonClicked : BinSearchEvent
    data object CloseBottomSheet : BinSearchEvent
}