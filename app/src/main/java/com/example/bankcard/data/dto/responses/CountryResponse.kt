package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("alpha2")
    val alpha2: String? = null,

    @SerializedName("emoji")
    val emoji: String? = null,

    @SerializedName("currency")
    val currency: String? = null,

    @SerializedName("latitude")
    val latitude: Double? = null,

    @SerializedName("longitude")
    val longitude: Double? = null
)