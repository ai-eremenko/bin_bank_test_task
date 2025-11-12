package com.example.bankcard.domain.binsearch

import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface BinSearchInteractor{
    suspend fun getBinInfo(bin: String): Result<BinInfo>
}