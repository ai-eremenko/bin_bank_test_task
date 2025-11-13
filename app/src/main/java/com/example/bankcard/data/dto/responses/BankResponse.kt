package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class BankResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("city")
    val city: String?
)