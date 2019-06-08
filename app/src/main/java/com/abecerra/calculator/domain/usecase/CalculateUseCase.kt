package com.abecerra.calculator.domain.usecase

import com.abecerra.calculator.domain.repository.MathRepository

class CalculateUseCase(private val mathRepository: MathRepository) {

    fun calculate(expression: String) = mathRepository.calculate(expression)

    fun getHistory() = mathRepository.getHistory()

}