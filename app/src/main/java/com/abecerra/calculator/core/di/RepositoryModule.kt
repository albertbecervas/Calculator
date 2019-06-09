package com.abecerra.calculator.core.di

import com.abecerra.calculator.data.repository.BankAccountRepositoryImpl
import com.abecerra.calculator.data.repository.CurrencyRepositoryImpl
import com.abecerra.calculator.data.repository.MathRepositoryImpl
import com.abecerra.calculator.data.repository.TransactionRepositoryImpl
import com.abecerra.calculator.domain.repository.BankAccountRepository
import com.abecerra.calculator.domain.repository.CurrencyRepository
import com.abecerra.calculator.domain.repository.MathRepository
import com.abecerra.calculator.domain.repository.TransactionsRepository
import org.koin.dsl.module

object RepositoryModule {

    fun get() = module {
        single<BankAccountRepository> { BankAccountRepositoryImpl() }
        single<TransactionsRepository> { TransactionRepositoryImpl(get()) }
        single<MathRepository> { MathRepositoryImpl(get()) }
        single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    }

}