package com.abecerra.calculator.domain.repository

import com.abecerra.calculator.presentation.model.CalculationHistory
import io.reactivex.Single

interface MathRepository {

    fun calculate(expression: String): Single<Float>

    fun getHistory(): Single<List<CalculationHistory>>

}