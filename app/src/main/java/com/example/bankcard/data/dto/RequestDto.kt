package com.example.bankcard.data.dto

sealed interface RequestDto {
    data class NumberRequest(
        val length: Int?,
        val luhn: Boolean?
    ) : RequestDto

    data class SchemeRequest(
        val scheme: String?
    ) : RequestDto

    data class TypeRequest(
        val type: String?
    ) : RequestDto

    data class BrandRequest(
        val brand: String?
    ) : RequestDto

    data class PrepaidRequest(
        val prepaid: Boolean?
    ) : RequestDto

    data class CountryRequest(
        val name: String?,
        val alpha2: String?,
        val emoji: String?,
        val currency: String?,
        val latitude: Double?,
        val longitude: Double?
    ) : RequestDto

    data class BankRequest(
        val name: String?,
        val url: String?,
        val phone: String?,
        val city: String?
    ) : RequestDto

    data class BinInfoRequest(
        val number: NumberRequest?,
        val scheme: String?,
        val type: String?,
        val brand: String?,
        val prepaid: Boolean?,
        val country: CountryRequest?,
        val bank: BankRequest?
    ) : RequestDto
}