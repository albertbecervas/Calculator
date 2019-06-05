package com.abecerra.calculator.data.repository

import com.abecerra.calculator.data.dto.BankAccountDto
import com.abecerra.calculator.data.dto.mapper.BankAccountDtoMapper
import com.abecerra.calculator.domain.model.BankAccountModel
import com.abecerra.calculator.domain.repository.BankAccountRepository
import io.reactivex.Single

class BankAccountRepositoryImpl : BankAccountRepository {

    override fun getBankAccount(): Single<BankAccountModel> {
        return Single.create {
            Thread.sleep(2000L)
            it.onSuccess(BankAccountDtoMapper.map(BankAccountDto()))
        }
    }

}