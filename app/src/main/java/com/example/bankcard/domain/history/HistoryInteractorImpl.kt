package com.example.bankcard.domain.history

import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {

    override fun getHistory(): Flow<List<BinInfo>> {
        return historyRepository.getHistory()
    }

    override suspend fun deleteFromHistory(bin: String) {
        historyRepository.deleteFromHistory(bin)
    }

    override suspend fun clearHistory() {
        historyRepository.clearHistory()
    }
}