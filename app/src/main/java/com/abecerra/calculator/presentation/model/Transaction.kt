package com.abecerra.calculator.presentation.model

data class Transaction(
    var name: String,
    var amount: Int,
    var date: String,
    var isReceived: Boolean
)