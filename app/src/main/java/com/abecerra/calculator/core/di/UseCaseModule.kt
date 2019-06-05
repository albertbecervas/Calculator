package com.abecerra.calculator.core.di

import com.abecerra.calculator.domain.usecase.BankAccountUseCase
import com.abecerra.calculator.domain.usecase.CalculateUseCase
import com.abecerra.calculator.domain.usecase.TransactionsUseCase
import org.koin.dsl.module

object UseCaseModule {

    fun get() = module {
        single { BankAccountUseCase(get()) }
        single { TransactionsUseCase(get()) }
        single { CalculateUseCase(get()) }
    }

}