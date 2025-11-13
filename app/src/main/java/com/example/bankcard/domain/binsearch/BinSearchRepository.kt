package com.example.bankcard.domain.binsearch

import com.example.bankcard.domain.model.BinInfo

interface BinSearchRepository {
    suspend fun getBinInfo(bin: String): Result<BinInfo>
}