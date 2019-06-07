package com.abecerra.calculator.data.dto

import com.squareup.moshi.Json

data class TransactionDto(
    var id: String,
    var name: String,
    @Json(name = "money")
    var amount: String,
    var date: String,
    var isReceived: Boolean
)