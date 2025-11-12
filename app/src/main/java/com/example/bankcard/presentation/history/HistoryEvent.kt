package com.example.bankcard.presentation.history

sealed interface HistoryEvent {
    object LoadHistory : HistoryEvent
    data class DeleteItem(val bin: String) : HistoryEvent
    object ClearError : HistoryEvent
}