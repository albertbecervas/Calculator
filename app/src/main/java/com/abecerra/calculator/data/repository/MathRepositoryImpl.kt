package com.abecerra.calculator.data.repository

import com.abecerra.calculator.core.math.Calculator
import com.abecerra.calculator.data.db.dao.ComputedCalculationsDao
import com.abecerra.calculator.data.db.entities.ComputedCalculationEntity
import com.abecerra.calculator.domain.repository.MathRepository
import com.abecerra.calculator.presentation.model.CalculationHistory
import com.abecerra.calculator.presentation.model.mapper.CalculationHistoryMapper
import io.reactivex.Single
import java.util.*

class MathRepositoryImpl(private val computedCalculationsDao: ComputedCalculationsDao) : MathRepository {

    override fun calculate(expression: String): Single<Float> {
        return Single.create { emitter ->
            try {
                val expressionResult = Calculator.evaluate(expression).toFloat()
                saveResultToDb(expressionResult, expression)
                emitter.onSuccess(expressionResult)
            } catch (e: Exception) {
                emitter.onError(Throwable(e.localizedMessage))
            }
        }
    }

    private fun saveResultToDb(expressionResult: Float, expression: String) {
        val computedCalculationEntity = ComputedCalculationEntity(expressionResult, expression)
        computedCalculationEntity.time = Date().time
        computedCalculationsDao.insert(computedCalculationEntity)
    }

    override fun getHistory(): Single<List<CalculationHistory>> {
        return Single.create { emitter ->
            val calculations = computedCalculationsDao.getCalculations().map {
                CalculationHistoryMapper.map(it)
            }
            emitter.onSuccess(calculations)
        }
    }
}