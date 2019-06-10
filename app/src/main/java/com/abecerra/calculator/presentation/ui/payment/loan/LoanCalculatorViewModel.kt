package com.abecerra.calculator.presentation.ui.payment.loan

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.context
import com.abecerra.calculator.core.utils.extensions.updateData

class LoanCalculatorViewModel : BaseViewModel() {

    var totalValue: Float? = null
    var rate: Float? = null
    var period: Float? = null

    var calculatedValue = MutableLiveData<Data<String>>()
    var monthlyPay = MutableLiveData<Data<String>>()
    var calculatedInterest = MutableLiveData<Data<String>>()

    fun calculateInterest() {
        totalValue?.let { totalValue ->
            rate?.let { rate ->
                period?.let { period ->
                    val interest = rate / 100 * totalValue * period
                    val totalValueCalculated = totalValue + interest
                    val monthlyPayCalculated = totalValueCalculated / 12
                    calculatedValue.updateData(context.getString(R.string.final_value, totalValueCalculated.toString()))
                    monthlyPay.updateData(context.getString(R.string.monthly_pay, monthlyPayCalculated.toString()))
                    calculatedInterest.updateData(context.getString(R.string.interest, interest.toString()))
                }
            }
        }
    }

}