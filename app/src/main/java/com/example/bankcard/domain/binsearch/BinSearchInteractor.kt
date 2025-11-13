package com.example.bankcard.domain.binsearch

import com.example.bankcard.domain.model.BinInfo

interface BinSearchInteractor{
    suspend fun getBinInfo(bin: String): Result<BinInfo>
}