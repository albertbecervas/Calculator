package com.abecerra.calculator.core.math;

import com.abecerra.calculator.R
import com.abecerra.calculator.core.utils.extensions.context
import net.objecthunter.exp4j.ExpressionBuilder

object Calculator {

    @Throws(Exception::class)
    fun evaluate(expression: String): Double {
        return ExpressionBuilder(expression).build().evaluate()
    }

    fun getEncodedValue(value: String): String {
        return when (value) {
            context.getString(R.string.sin_symbol), context.getString(R.string.cos_symbol),
            context.getString(R.string.tan_symbol), context.getString(R.string.ln_symbol),
            context.getString(R.string.log_symbol) -> {
                "$value("
            }
            else -> value
        }
    }
}