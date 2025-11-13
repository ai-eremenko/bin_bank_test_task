package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class BinInfoResponse(
    @SerializedName("number")
    val number: NumberResponse?,

    @SerializedName("scheme")
    val scheme: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("brand")
    val brand: String?,

    @SerializedName("prepaid")
    val prepaid: Boolean?,

    @SerializedName("country")
    val country: CountryResponse?,

    @SerializedName("bank")
    val bank: BankResponse?
)