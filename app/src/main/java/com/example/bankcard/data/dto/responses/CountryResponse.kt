package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("alpha2")
    val alpha2: String?,

    @SerializedName("emoji")
    val emoji: String?,

    @SerializedName("currency")
    val currency: String?,

    @SerializedName("latitude")
    val latitude: Double?,

    @SerializedName("longitude")
    val longitude: Double?
)