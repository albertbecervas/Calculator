package com.abecerra.calculator.presentation.calculator

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.math.Calculator.getEncodedValue
import com.abecerra.calculator.core.utils.extensions.*
import com.abecerra.calculator.domain.usecase.CalculateUseCase
import io.reactivex.disposables.CompositeDisposable

class CalculatorViewModel(private val calculateUseCase: CalculateUseCase) : BaseViewModel() {

    val operationData = MutableLiveData<Data<String>>()
    val result = MutableLiveData<Data<Float>>()

    fun addValueToExpression(value: String) {
        val currentOperation = operationData.value?.data ?: ""
        val updatedOperation = currentOperation + getEncodedValue(value)
        operationData.updateData(updatedOperation)
    }

    fun clearLastCharacter() {
        val currentOperation = operationData.value?.data ?: ""
        if (currentOperation.isNotBlank()) {
            operationData.updateData(currentOperation.dropLast(1))
        } else {
            operationData.updateData("")
        }
        result.showLoading()
    }


    fun clearData() {
        operationData.updateData("")
        result.showLoading()
    }

    fun calculateCurrent() {
        val expression = operationData.value?.data ?: ""
        if (expression.isNotEmpty()) {
            calculateUseCase.calculate(expression)
                .subscribe({
                    result.showLoading()
                }, {
                    result.updateData(it)
                    operationData.updateData(null)
                }, {
                    result.showError(it.message)
                }, CompositeDisposable())
        }
    }

}