package com.abecerra.calculator.core.di

import com.abecerra.calculator.core.utils.AppSharedPreferences
import org.koin.dsl.module

object AppModule {

    fun get() =
        listOf(
            ViewModelModule.get(),
            UseCaseModule.get(),
            RepositoryModule.get(),
            ApiModule.get(),
            NavigatorModule.get(),
            DatabaseModule.get(),
            module { single { AppSharedPreferences.getInstance() } }
        )
}