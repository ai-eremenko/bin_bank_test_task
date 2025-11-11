package com.example.bankcard.data.mapper

import com.example.bankcard.data.db.entity.BinInfoEntity
import com.example.bankcard.data.dto.BinInfoDto
import com.example.bankcard.data.dto.responses.BinInfoResponse
import com.example.bankcard.domain.model.BinInfo

object BinInfoMapper {

    fun mapToDto(bin: String, response: BinInfoResponse): BinInfoDto {
        return BinInfoDto(
            bin = bin,
            scheme = response.scheme,
            type = response.type,
            brand = response.brand,
            prepaid = response.prepaid,
            countryName = response.country?.name,
            countryAlpha2 = response.country?.alpha2,
            countryEmoji = response.country?.emoji,
            countryCurrency = response.country?.currency,
            countryLatitude = response.country?.latitude,
            countryLongitude = response.country?.longitude,
            bankName = response.bank?.name,
            bankUrl = response.bank?.url,
            bankPhone = response.bank?.phone,
            bankCity = response.bank?.city,
            numberLength = response.number?.length,
            numberLuhn = response.number?.luhn
        )
    }

    fun BinInfoDto.toBinInfo(): BinInfo {
        return BinInfo(
            bin = bin,
            scheme = scheme,
            type = type,
            brand = brand,
            prepaid = prepaid,
            countryName = countryName,
            countryAlpha2 = countryAlpha2,
            countryEmoji = countryEmoji,
            countryCurrency = countryCurrency,
            countryLatitude = countryLatitude,
            countryLongitude = countryLongitude,
            bankName = bankName,
            bankUrl = bankUrl,
            bankPhone = bankPhone,
            bankCity = bankCity,
            numberLength = numberLength,
            numberLuhn = numberLuhn,
            timestamp = System.currentTimeMillis()
        )
    }

    fun BinInfo.toEntity(): BinInfoEntity {
        return BinInfoEntity(
            bin = bin,
            scheme = scheme,
            type = type,
            brand = brand,
            prepaid = prepaid,
            countryName = countryName,
            countryAlpha2 = countryAlpha2,
            countryEmoji = countryEmoji,
            countryCurrency = countryCurrency,
            countryLatitude = countryLatitude,
            countryLongitude = countryLongitude,
            bankName = bankName,
            bankUrl = bankUrl,
            bankPhone = bankPhone,
            bankCity = bankCity,
            numberLength = numberLength,
            numberLuhn = numberLuhn,
            timestamp = timestamp
        )
    }

    fun BinInfoEntity.toBinInfo(): BinInfo {
        return BinInfo(
            bin = bin,
            scheme = scheme,
            type = type,
            brand = brand,
            prepaid = prepaid,
            countryName = countryName,
            countryAlpha2 = countryAlpha2,
            countryEmoji = countryEmoji,
            countryCurrency = countryCurrency,
            countryLatitude = countryLatitude,
            countryLongitude = countryLongitude,
            bankName = bankName,
            bankUrl = bankUrl,
            bankPhone = bankPhone,
            bankCity = bankCity,
            numberLength = numberLength,
            numberLuhn = numberLuhn,
            timestamp = timestamp
        )
    }
}