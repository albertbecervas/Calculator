package com.abecerra.calculator.domain.data

import java.util.*

data class TransactionModel(
    var name: String,
    var amount: String,
    var date: Date,
    var isReceived: Boolean
)