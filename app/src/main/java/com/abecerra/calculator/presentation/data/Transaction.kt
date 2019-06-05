package com.abecerra.calculator.presentation.data

data class Transaction(
    var name: String,
    var amount: String,
    var date: String,
    var isReceived: Boolean
)