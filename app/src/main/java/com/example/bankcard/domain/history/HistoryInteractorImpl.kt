package com.example.bankcard.domain.history

import com.example.bankcard.domain.model.BinInfo

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
) : HistoryInteractor {

    override suspend fun getHistory(): List<BinInfo> {
        return historyRepository.getHistory()
    }

    override suspend fun deleteFromHistory(bin: String) {
        historyRepository.deleteFromHistory(bin)
    }
}