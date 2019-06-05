package com.abecerra.calculator.presentation.home.transactions

import android.arch.lifecycle.MutableLiveData
import com.abecerra.calculator.core.base.BaseViewModel
import com.abecerra.calculator.core.utils.extensions.*
import com.abecerra.calculator.domain.usecase.TransactionsUseCase
import com.abecerra.calculator.presentation.data.Transaction
import com.abecerra.calculator.presentation.data.mapper.TransactionMapper
import io.reactivex.disposables.CompositeDisposable

class TransactionsViewModel(private val transactionsUseCase: TransactionsUseCase) : BaseViewModel() {

    val transactions = MutableLiveData<Data<List<Transaction>>>()

    fun getTransactions() {
        transactionsUseCase.getTransactions()
            .subscribe({
                transactions.showLoading()
            }, {
                transactions.updateData(TransactionMapper.map(it))
            }, {
                transactions.showError(it.message)
            }, CompositeDisposable())

    }

}