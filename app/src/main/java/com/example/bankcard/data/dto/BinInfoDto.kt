package com.example.bankcard.data.dto

data class BinInfoDto(
    val bin: String,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val countryName: String? = null,
    val countryAlpha2: String? = null,
    val countryEmoji: String? = null,
    val countryCurrency: String? = null,
    val countryLatitude: Double? = null,
    val countryLongitude: Double? = null,
    val bankName: String? = null,
    val bankUrl: String? = null,
    val bankPhone: String? = null,
    val bankCity: String? = null,
    val numberLength: Int? = null,
    val numberLuhn: Boolean? = null
)