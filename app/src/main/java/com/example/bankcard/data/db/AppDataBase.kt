package com.example.bankcard.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bankcard.data.db.dao.BinInfoDao
import com.example.bankcard.data.db.entity.BinInfoEntity

@Database(
    version = 4,
    entities = [BinInfoEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao
}