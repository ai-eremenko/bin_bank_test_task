package com.example.bankcard.presentation.binsearch

import com.example.bankcard.domain.model.BinInfo

data class BinSearchState(
    val searchQuery: String = "",
    val binInfo: BinInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showBottomSheet: Boolean = false
)