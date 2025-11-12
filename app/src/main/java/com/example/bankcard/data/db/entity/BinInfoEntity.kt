package com.example.bankcard.data.db.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "bin_history")
data class BinInfoEntity(
    @PrimaryKey
    val bin: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryName: String?,
    val countryAlpha2: String?,
    val countryEmoji: String?,
    val countryCurrency: String?,
    val countryLatitude: Double?,
    val countryLongitude: Double?,
    val bankName: String?,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?,
    val numberLength: Int?,
    val numberLuhn: Boolean?,
    val timestamp: Long = System.currentTimeMillis()
)
