package com.example.bankcard.data.binsearch

import com.example.bankcard.data.db.dao.BinInfoDao
import com.example.bankcard.data.mapper.BinInfoMapper
import com.example.bankcard.data.mapper.BinInfoMapper.toBinInfo
import com.example.bankcard.data.mapper.BinInfoMapper.toEntity
import com.example.bankcard.data.network.BinApiService
import com.example.bankcard.domain.binsearch.BinSearchRepository
import com.example.bankcard.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BinSearchRepositoryImpl(
    private val binApiService: BinApiService,
    private val binInfoDao: BinInfoDao,
    private val mapper: BinInfoMapper
) : BinSearchRepository {

    override suspend fun getBinInfo(bin: String): Result<BinInfo> {
        return try {
            val response = binApiService.getBinInfo(bin)
            val dto = mapper.mapToDto(bin, response)
            val binInfo = dto.toBinInfo()

            binInfoDao.insert(binInfo.toEntity())

            Result.success(binInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getBinHistory(): Flow<List<BinInfo>> {
        return binInfoDao.getAll().map { entities ->
            entities.map { it.toBinInfo() }
        }
    }

    override suspend fun deleteBinFromHistory(bin: String) {
        binInfoDao.deleteByBin(bin)
    }
}