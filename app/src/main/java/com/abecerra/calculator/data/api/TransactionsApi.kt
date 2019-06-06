package com.abecerra.calculator.data.api

import com.abecerra.calculator.data.dto.ResponseDto
import com.abecerra.calculator.data.dto.TransactionDto
import io.reactivex.Single
import retrofit2.http.GET

interface TransactionsApi {

    @GET("/api/json/get/NJDIoNzCU")
    fun getTransactions(): Single<ResponseDto<List<TransactionDto>>>

}