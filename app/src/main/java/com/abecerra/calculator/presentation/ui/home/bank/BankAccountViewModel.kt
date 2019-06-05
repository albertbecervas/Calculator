package com.abecerra.calculator.presentation.ui.home.bank

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.*
import com.abecerra.calculator.domain.usecase.BankAccountUseCase
import com.abecerra.calculator.presentation.model.BankAccount
import com.abecerra.calculator.presentation.model.mapper.BankAccountMapper
import io.reactivex.disposables.CompositeDisposable

class BankAccountViewModel(private val bankAccountUseCase: BankAccountUseCase) : BaseViewModel() {

    val bankAccount = MutableLiveData<Data<BankAccount>>()

    fun getBankAccount() {
        bankAccountUseCase.getBankAccount()
            .subscribe({
                bankAccount.showLoading()
            }, {
                bankAccount.updateData(BankAccountMapper.map(it))
            }, {
                bankAccount.showError(it.message)
            }, CompositeDisposable())

    }


}