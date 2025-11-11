package com.example.bankcard.domain.binsearch

import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

class BinSearchInteractorImpl (
    private val binSearchRepository: BinSearchRepository
): BinSearchInteractor {

    override suspend fun getBinInfo(bin: String): Result<BinInfo> {
        return binSearchRepository.getBinInfo(bin)
    }

    override fun getBinHistory(): Flow<List<BinInfo>> {
        return binSearchRepository.getBinHistory()
    }

    override suspend fun deleteBinFromHistory(bin: String) {
        binSearchRepository.deleteBinFromHistory(bin)
    }
}