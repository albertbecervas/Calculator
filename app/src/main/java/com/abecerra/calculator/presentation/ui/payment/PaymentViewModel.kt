package com.abecerra.calculator.presentation.ui.payment

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.updateData
import com.abecerra.calculator.presentation.model.Currency

class PaymentViewModel : BaseViewModel() {

    val currencyList = MutableLiveData<Data<List<Currency>>>()

    fun getCurrencies() {
        currencyList.updateData(listOf(Currency(), Currency("Dollar"), Currency("Test"), Currency("india")))
    }

}