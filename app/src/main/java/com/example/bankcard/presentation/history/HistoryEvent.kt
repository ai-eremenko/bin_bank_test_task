package com.example.bankcard.presentation.history

import com.example.bankcard.domain.model.BinInfo

sealed interface HistoryEvent {
    object LoadHistory : HistoryEvent
    data class DeleteItem(val bin: String) : HistoryEvent
    object ClearError : HistoryEvent
    data class ShowBottomSheet(val show: Boolean, val binInfo: BinInfo? = null) : HistoryEvent
}