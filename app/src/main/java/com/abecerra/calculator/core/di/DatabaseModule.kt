package com.abecerra.calculator.core.di

import com.abecerra.calculator.data.db.CalculatorDataBase
import org.koin.dsl.module

object DatabaseModule {

    fun get() = module {
        single { getDatabase() }
        single { getComputedCalculationsDao(get()) }
    }

    private fun getDatabase(): CalculatorDataBase {
        return CalculatorDataBase.build()
    }

    private fun getComputedCalculationsDao(dataBase: CalculatorDataBase) = dataBase.getComputedCalculationsDao()


}