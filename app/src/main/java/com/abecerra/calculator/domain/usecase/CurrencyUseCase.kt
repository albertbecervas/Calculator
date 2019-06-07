package com.abecerra.calculator.domain.usecase

import com.abecerra.calculator.domain.repository.CurrencyRepository

class CurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    fun getCurrencyList() = currencyRepository.getCurrencyList()

    fun getCurrencyConversion(from: String, to: String) = currencyRepository.getCurrencyConversion(from, to)

}