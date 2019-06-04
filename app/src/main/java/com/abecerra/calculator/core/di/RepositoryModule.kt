package com.abecerra.calculator.core.di

import com.abecerra.calculator.data.repository.MathRepositoryImpl
import com.abecerra.calculator.domain.repository.MathRepository
import org.koin.dsl.module

object RepositoryModule {

    fun get() = module {
        single<MathRepository> { MathRepositoryImpl() }
    }

}