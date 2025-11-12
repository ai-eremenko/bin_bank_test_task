package com.example.bankcard.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bankcard.data.db.entity.BinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binInfo: BinInfoEntity)

    @Query("SELECT * FROM bin_history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<BinInfoEntity>>

    @Query("DELETE FROM bin_history WHERE bin = :bin")
    suspend fun deleteByBin(bin: String)

    @Query("DELETE FROM bin_history")
    suspend fun clearAll()
}