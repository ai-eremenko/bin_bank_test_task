package com.example.bankcard.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bankcard.data.db.dao.BinInfoDao
import com.example.bankcard.data.db.entity.BinInfoEntity

@Database(
    version = 1,
    entities = [BinInfoEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao
}