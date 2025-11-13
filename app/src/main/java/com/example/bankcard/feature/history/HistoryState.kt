package com.example.bankcard.feature.history

import androidx.annotation.StringRes
import com.example.bankcard.domain.model.BinInfo

data class HistoryState(
    val history: List<BinInfo> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes
    val errorResId: Int? = null,
    val showBottomSheet: Boolean = false,
    val selectedBinInfo: BinInfo? = null
)