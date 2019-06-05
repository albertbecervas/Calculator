package com.abecerra.calculator.domain.repository

import com.abecerra.calculator.data.dto.ResponseDto
import com.abecerra.calculator.domain.model.TransactionModel
import io.reactivex.Single

interface TransactionsRepository {

    fun getTransactions(): Single<List<TransactionModel>>

    fun setTransaction(name: String, amount: String): Single<ResponseDto<String>>

}