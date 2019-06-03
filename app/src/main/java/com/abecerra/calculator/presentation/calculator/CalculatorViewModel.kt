package com.abecerra.calculator.presentation.calculator

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.updateData

class CalculatorViewModel : BaseViewModel() {

    val operationData = MutableLiveData<Data<String>>()

    fun addNumber(number: Int) {
        val currentOperation = operationData.value?.data ?: ""
        val updatedOperation = currentOperation + number
        operationData.updateData(updatedOperation)
    }

    fun clearData() {
        operationData.updateData("")
    }

}