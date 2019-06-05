package com.abecerra.calculator.data.dto

import java.util.*

data class TransactionDto(
    var name: String = "TRASPASO",
    var amount: String = "500€",
    var date: String = Date().toString(),
    var isReceived: Boolean = true
)