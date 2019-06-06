package com.abecerra.calculator.domain.repository

import com.abecerra.calculator.domain.model.CurrencyModel
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencyList(): Single<List<CurrencyModel>>

    fun getCurrencyConversion(from: String, to: String)

}