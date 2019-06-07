package com.abecerra.calculator.data.repository

import com.abecerra.calculator.data.api.TransactionsApi
import com.abecerra.calculator.domain.model.CurrencyModel
import com.abecerra.calculator.domain.repository.CurrencyRepository
import io.reactivex.Single

class CurrencyRepositoryImpl(private val api: TransactionsApi) : CurrencyRepository {
    override fun getCurrencyList(): Single<List<CurrencyModel>> {
        return Single.create({})
    }

    override fun getCurrencyConversion(from: String, to: String) {
    }

}