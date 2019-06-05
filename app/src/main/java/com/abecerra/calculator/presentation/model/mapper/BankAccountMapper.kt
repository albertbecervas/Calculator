package com.abecerra.calculator.presentation.model.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.domain.model.BankAccountModel
import com.abecerra.calculator.presentation.model.BankAccount

object BankAccountMapper : BaseMapper<BankAccountModel, BankAccount>() {

    override fun map(from: BankAccountModel): BankAccount {
        return with(from) {
            BankAccount(
                accountNumber = accountNumber,
                money = money
            )
        }
    }

}