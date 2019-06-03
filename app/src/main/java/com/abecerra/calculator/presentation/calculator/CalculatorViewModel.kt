package com.abecerra.calculator.presentation.calculator

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.context
import com.abecerra.calculator.core.utils.extensions.updateData
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel : BaseViewModel() {

    val operationData = MutableLiveData<Data<String>>()
    val result = MutableLiveData<Data<String>>()

    //todo implement better solution as ENUM with sates

    // Represent whether the lastly pressed key is numeric or not
    private var lastNumeric: Boolean = false
    // If true, do not allow to add another DOT
    private var lastDot: Boolean = false

    fun addNumber(number: Int) {
        val currentOperation = operationData.value?.data ?: ""
        val updatedOperation = currentOperation + number
        operationData.updateData(updatedOperation)
        lastNumeric = true
    }

    fun addOperation(operation: String) {
        val currentOperation = operationData.value?.data ?: ""
        if (currentOperation.isNotBlank()) {
            operationData.updateData(currentOperation + operation)
            lastNumeric = false
            lastDot = false
        }
    }

    fun addDot() {
        val currentOperation = operationData.value?.data ?: ""
        if (currentOperation.isNotBlank()) {
            operationData.updateData("$currentOperation.")
            lastDot = true
            lastNumeric = false
        }
    }

    fun clearLastCharacter() {
        val currentOperation = operationData.value?.data ?: ""
        if (currentOperation.isNotBlank()) {
            operationData.updateData(currentOperation.substring(0, currentOperation.length - 1))
        }
    }


    fun clearData() {
        operationData.updateData("")
        result.updateData("")
        lastNumeric = false
        lastDot = false
    }

    fun calculate() {
        val expression = operationData.value?.data ?: ""
        if (expression.isNotEmpty() && lastNumeric) {
            val expressionBuilder = ExpressionBuilder(expression).build()
            try {
                val expressionResult = expressionBuilder.evaluate()
                result.updateData(expressionResult.toString())
            } catch (e: Exception) {

            }
        }
    }

    companion object {
        enum class Operation(value: String) {
            SUM(context.getString(R.string.sum_symbol)), DIF(context.getString(R.string.dif_symbol)),
            DIV(context.getString(R.string.divide_symbol)), MUL(context.getString(R.string.multiply_symbol))
        }
    }

}