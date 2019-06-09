package com.abecerra.calculator.data.repository

import com.abecerra.calculator.data.api.TransactionsApi
import com.abecerra.calculator.data.api.dto.ResponseDto
import com.abecerra.calculator.data.api.dto.mapper.TransactionDtoMapper
import com.abecerra.calculator.domain.model.TransactionModel
import com.abecerra.calculator.domain.repository.TransactionsRepository
import io.reactivex.Single

class TransactionRepositoryImpl(private val api: TransactionsApi) : TransactionsRepository {
    override fun getTransactions(): Single<List<TransactionModel>> {
        return api.getTransactions().flatMap {
            Single.just(it.data)
        }.map {
            TransactionDtoMapper.map(it)
        }
    }

    override fun setTransaction(name: String, amount: String): Single<ResponseDto<String>> {
        return Single.create {
            Thread.sleep(2000L)
            it.onError(Throwable())
        }
    }


}