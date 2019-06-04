package com.abecerra.calculator.core.di

import com.abecerra.calculator.domain.usecase.CalculateUseCase
import org.koin.dsl.module

object UseCaseModule {

    fun get() = module {
        single { CalculateUseCase(get()) }
    }

}