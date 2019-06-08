package com.abecerra.calculator.presentation.ui.calculator.history

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.subscribe
import com.abecerra.calculator.core.utils.extensions.updateData
import com.abecerra.calculator.domain.usecase.CalculateUseCase
import com.abecerra.calculator.presentation.model.CalculationHistory
import io.reactivex.disposables.CompositeDisposable

class CalculatorHistoryViewModel(private val calculateUseCase: CalculateUseCase) : BaseViewModel() {

    val history = MutableLiveData<Data<List<CalculationHistory>>>()

    fun getHistory() {
        calculateUseCase.getHistory()
            .subscribe({

            }, {
                history.updateData(it)
            }, {

            }, CompositeDisposable())

    }

}