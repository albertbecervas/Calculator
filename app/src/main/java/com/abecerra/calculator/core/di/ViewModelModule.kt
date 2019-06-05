package com.abecerra.calculator.core.di

import com.abecerra.calculator.presentation.ui.calculator.CalculatorViewModel
import com.abecerra.calculator.presentation.ui.home.bank.BankAccountViewModel
import com.abecerra.calculator.presentation.ui.home.transactions.TransactionsViewModel
import com.abecerra.calculator.presentation.ui.payment.PaymentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    fun get() = module {
        viewModel { BankAccountViewModel(get()) }
        viewModel { TransactionsViewModel(get()) }
        viewModel { PaymentViewModel() }
        viewModel { CalculatorViewModel(get()) }
    }

}