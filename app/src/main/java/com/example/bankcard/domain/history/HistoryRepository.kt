package com.example.bankcard.domain.history

import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(): Flow<List<BinInfo>>
    suspend fun deleteFromHistory(bin: String)
}