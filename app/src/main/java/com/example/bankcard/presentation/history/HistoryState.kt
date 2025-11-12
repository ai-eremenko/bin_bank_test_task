package com.example.bankcard.presentation.history

import com.example.bankcard.domain.model.BinInfo

data class HistoryState(
    val history: List<BinInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)