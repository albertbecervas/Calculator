package com.abecerra.calculator.presentation.ui.payment

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.*
import com.abecerra.calculator.domain.usecase.CurrencyUseCase
import com.abecerra.calculator.presentation.model.Currency
import com.abecerra.calculator.presentation.model.mapper.CurrencyMapper
import io.reactivex.disposables.CompositeDisposable

class PaymentViewModel(private val currencyUseCase: CurrencyUseCase) : BaseViewModel() {

    val currencyList = MutableLiveData<Data<List<Currency>>>()

    fun getCurrencies() {
        currencyUseCase.getCurrencyList()
            .subscribe({
                currencyList.showLoading()
            }, {
                currencyList.updateData(CurrencyMapper.map(it))
            }, {
                currencyList.showError(it.message)
            }, CompositeDisposable())
    }

}