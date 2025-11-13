package com.example.bankcard.feature.binsearch

import androidx.annotation.StringRes
import com.example.bankcard.domain.model.BinInfo

data class BinSearchState(
    val searchQuery: String = "",
    val binInfo: BinInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    @StringRes
    val errorResId: Int? = null,
    val showBottomSheet: Boolean = false
)