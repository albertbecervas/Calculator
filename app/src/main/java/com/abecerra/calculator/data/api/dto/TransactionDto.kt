package com.abecerra.calculator.data.api.dto

import com.squareup.moshi.Json

data class TransactionDto(
    var id: String,
    var name: String,
    @Json(name = "money")
    var amount: Int,
    var date: String,
    var isReceived: Boolean
)