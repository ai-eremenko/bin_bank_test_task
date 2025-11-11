package com.example.bankcard.data.dto.responses

import com.google.gson.annotations.SerializedName

data class BinInfoResponse(
    @SerializedName("number")
    val number: NumberResponse? = null,

    @SerializedName("scheme")
    val scheme: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("brand")
    val brand: String? = null,

    @SerializedName("prepaid")
    val prepaid: Boolean? = null,

    @SerializedName("country")
    val country: CountryResponse? = null,

    @SerializedName("bank")
    val bank: BankResponse? = null
)