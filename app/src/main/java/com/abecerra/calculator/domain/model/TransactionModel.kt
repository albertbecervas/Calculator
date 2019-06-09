package com.abecerra.calculator.domain.model

import java.util.*

data class TransactionModel(
    var name: String,
    var amount: Int,
    var date: Date,
    var isReceived: Boolean
)