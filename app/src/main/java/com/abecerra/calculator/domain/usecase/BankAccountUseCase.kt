package com.abecerra.calculator.domain.usecase

import com.abecerra.calculator.domain.repository.BankAccountRepository

class BankAccountUseCase(private val bankAccountRepository: BankAccountRepository) {

    fun getBankAccount() = bankAccountRepository.getBankAccount()

}