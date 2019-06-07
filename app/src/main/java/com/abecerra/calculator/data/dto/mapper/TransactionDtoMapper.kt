package com.abecerra.calculator.data.dto.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.core.utils.DateFormatter
import com.abecerra.calculator.data.dto.TransactionDto
import com.abecerra.calculator.domain.model.TransactionModel

object TransactionDtoMapper : BaseMapper<TransactionDto, TransactionModel>() {

    override fun map(from: TransactionDto): TransactionModel {
        return with(from) {
            TransactionModel(
                name = name,
                amount = amount,
                date = DateFormatter.formatTransactionToDate(date),
                isReceived = isReceived
            )
        }
    }
}