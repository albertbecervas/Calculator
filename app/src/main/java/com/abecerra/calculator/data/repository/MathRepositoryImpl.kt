package com.abecerra.calculator.data.repository

import com.abecerra.calculator.core.math.Calculator
import com.abecerra.calculator.domain.repository.MathRepository
import io.reactivex.Single

class MathRepositoryImpl : MathRepository {

    override fun calculate(expression: String): Single<Float> {
        return Single.create { emitter ->
            try {
                val expressionResult = Calculator.evaluate(expression)
                emitter.onSuccess(expressionResult.toFloat())
            } catch (e: Exception) {
                emitter.onError(Throwable(e.localizedMessage))
            }
        }
    }
}