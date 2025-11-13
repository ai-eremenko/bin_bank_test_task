package com.example.bankcard.feature.history

import com.example.bankcard.domain.model.BinInfo

sealed interface HistoryEvent {
    object LoadHistory : HistoryEvent
    data class DeleteItem(val bin: String) : HistoryEvent
    data class ShowBottomSheet(val show: Boolean, val binInfo: BinInfo? = null) : HistoryEvent
}