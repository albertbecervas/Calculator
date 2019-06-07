package com.abecerra.calculator.domain.repository

import com.abecerra.calculator.domain.model.BankAccountModel
import io.reactivex.Single

interface BankAccountRepository {

    fun getBankAccount(): Single<BankAccountModel>

}