package com.abecerra.calculator.presentation.data.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.core.utils.DateFormatter
import com.abecerra.calculator.domain.data.TransactionModel
import com.abecerra.calculator.presentation.data.Transaction

object TransactionMapper : BaseMapper<TransactionModel, Transaction>() {
    override fun map(from: TransactionModel): Transaction {
        return with(from) {
            Transaction(
                name = name,
                amount = amount,
                date = DateFormatter.formatTransactionFromDate(date),
                isReceived = isReceived
            )
        }
    }

}