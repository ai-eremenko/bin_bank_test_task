package com.example.bankcard.data.history

import com.example.bankcard.data.db.dao.BinInfoDao
import com.example.bankcard.data.mapper.BinInfoMapper.toBinInfo
import com.example.bankcard.domain.history.HistoryRepository
import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryRepositoryImpl(
    private val binInfoDao: BinInfoDao
) : HistoryRepository {

    override suspend fun getHistory(): List<BinInfo> {
        return withContext(Dispatchers.IO) {
            binInfoDao.getAll().map { it.toBinInfo() }
        }
    }

    override suspend fun deleteFromHistory(bin: String) {
        return withContext(Dispatchers.IO) {
            binInfoDao.deleteByBin(bin)
        }
    }
}