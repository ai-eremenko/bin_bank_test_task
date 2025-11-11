package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class BankResponse(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("city")
    val city: String? = null
)