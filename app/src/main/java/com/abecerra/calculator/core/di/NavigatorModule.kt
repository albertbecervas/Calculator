package com.abecerra.calculator.core.di

import android.content.Context
import com.abecerra.calculator.core.navigator.Navigator
import com.abecerra.calculator.core.navigator.NavigatorImpl
import org.koin.dsl.module

object NavigatorModule {
    fun get() = module {
        single<Navigator> { (context: Context) -> NavigatorImpl(context) }
    }
}