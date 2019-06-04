package com.abecerra.calculator.core.navigator

import android.content.Context
import com.abecerra.calculator.presentation.calculator.CalculatorActivity
import org.jetbrains.anko.startActivity

class NavigatorImpl(private var context: Context) : Navigator {

    override fun navigateToCalculator() {
        with(context) {
            startActivity<CalculatorActivity>()
        }
    }

}