package com.abecerra.calculator.core.di

import com.abecerra.calculator.presentation.calculator.CalculatorViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    fun get() = module {
        viewModel {
            CalculatorViewModel(get())
        }
    }

}