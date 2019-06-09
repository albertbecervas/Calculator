package com.abecerra.calculator.data.api.dto.mapper

import com.abecerra.calculator.core.base.BaseMapper
import com.abecerra.calculator.core.encryption.EncryptionHelper
import com.abecerra.calculator.data.api.dto.BankAccountDto
import com.abecerra.calculator.domain.model.BankAccountModel

object BankAccountDtoMapper : BaseMapper<BankAccountDto, BankAccountModel>() {

    override fun map(from: BankAccountDto): BankAccountModel {
        return with(from) {
            BankAccountModel(
                accountNumber = EncryptionHelper.decrypt(account),
                money = EncryptionHelper.decrypt(money)
            )
        }
    }

}