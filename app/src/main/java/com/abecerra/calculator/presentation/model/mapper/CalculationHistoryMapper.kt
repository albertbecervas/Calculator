package com.abecerra.calculator.presentation.model.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.data.db.entities.ComputedCalculationEntity
import com.abecerra.calculator.presentation.model.CalculationHistory

object CalculationHistoryMapper : BaseMapper<ComputedCalculationEntity, CalculationHistory>() {
    override fun map(from: ComputedCalculationEntity): CalculationHistory {
        return with(from) {
            CalculationHistory(
                operation, result.toString()
            )
        }
    }

}