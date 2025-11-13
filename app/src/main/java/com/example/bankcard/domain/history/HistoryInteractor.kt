package com.example.bankcard.domain.history

import com.example.bankcard.domain.model.BinInfo

interface HistoryInteractor {
    suspend fun getHistory(): List<BinInfo>
    suspend fun deleteFromHistory(bin: String)
}