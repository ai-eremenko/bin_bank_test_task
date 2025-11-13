package com.example.bankcard.domain.binsearch

import com.example.bankcard.domain.model.BinInfo

class BinSearchInteractorImpl (
    private val binSearchRepository: BinSearchRepository
): BinSearchInteractor {

    override suspend fun getBinInfo(bin: String): Result<BinInfo> {
        return binSearchRepository.getBinInfo(bin)
    }
}