package com.example.bankcard.data.history

import com.example.bankcard.data.db.dao.BinInfoDao
import com.example.bankcard.data.mapper.BinInfoMapper
import com.example.bankcard.data.mapper.BinInfoMapper.toBinInfo
import com.example.bankcard.domain.history.HistoryRepository
import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(
    private val binInfoDao: BinInfoDao,
    private val mapper: BinInfoMapper
) : HistoryRepository {

    override fun getHistory(): Flow<List<BinInfo>> {
        return binInfoDao.getAll().map { entities ->
            entities.map { it.toBinInfo() }
        }
    }

    override suspend fun deleteFromHistory(bin: String) {
        binInfoDao.deleteByBin(bin)
    }

    override suspend fun clearHistory() {
         binInfoDao.clearAll()
    }
}