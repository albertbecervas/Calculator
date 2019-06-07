package com.abecerra.calculator.data.dto.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.data.dto.CurrencyDto
import com.abecerra.calculator.domain.model.CurrencyModel

object CurrencyDtoMapper : BaseMapper<CurrencyDto, CurrencyModel>() {
    override fun map(from: CurrencyDto): CurrencyModel {
        return with(from) {
            CurrencyModel(
                id = id,
                currencyName = currencyName,
                currencySymbol = currencySymbol
            )
        }
    }

}