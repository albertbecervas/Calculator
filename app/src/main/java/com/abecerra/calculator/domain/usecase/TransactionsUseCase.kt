package com.abecerra.calculator.domain.usecase

import com.abecerra.calculator.domain.repository.TransactionsRepository

class TransactionsUseCase(private val transactionsRepository: TransactionsRepository) {

    fun getTransactions() = transactionsRepository.getTransactions()

    fun setTransaction(name: String, amount: String) = transactionsRepository.setTransaction(name, amount)

}