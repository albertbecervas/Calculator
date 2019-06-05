package com.abecerra.calculator.data.repository

import com.abecerra.calculator.data.dto.ResponseDto
import com.abecerra.calculator.data.dto.TransactionDto
import com.abecerra.calculator.data.dto.mapper.TransactionDtoMapper
import com.abecerra.calculator.domain.data.TransactionModel
import com.abecerra.calculator.domain.repository.TransactionsRepository
import io.reactivex.Single

class TransactionRepositoryImpl() : TransactionsRepository {
    override fun getTransactions(): Single<List<TransactionModel>> {
        return Single.create {
            it.onSuccess(TransactionDtoMapper.map(arrayListOf<TransactionDto>()))
        }

    }

    override fun setTransaction(name: String, amount: String): Single<ResponseDto<String>> {
        return Single.create {
            it.onError(Throwable())
        }

    }


}