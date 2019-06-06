package com.abecerra.calculator.presentation.model.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.domain.model.CurrencyModel
import com.abecerra.calculator.presentation.model.Currency

object CurrencyMapper : BaseMapper<CurrencyModel, Currency>() {
    override fun map(from: CurrencyModel): Currency {
        return with(from) {
            Currency(
                id = id,
                currencyName = currencyName,
                currencySymbol = currencySymbol
            )
        }
    }
}