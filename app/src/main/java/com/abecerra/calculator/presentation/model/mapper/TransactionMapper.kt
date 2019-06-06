package com.abecerra.calculator.presentation.model.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.core.utils.DateFormatter
import com.abecerra.calculator.domain.model.TransactionModel
import com.abecerra.calculator.presentation.model.Transaction

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

    override fun map(from: Collection<TransactionModel>): List<Transaction> {
        return super.map(from).sortedBy { it.date }
    }

}