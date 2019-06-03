package com.abecerra.calculator.core.base

import android.support.v4.app.Fragment
import com.abecerra.calculator.core.navigator.Navigator
import com.abecerra.calculator.core.utils.AppSharedPreferences
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {

    protected val navigator: Navigator by inject { parametersOf(this.context) }
    protected val mPrefs: AppSharedPreferences by inject()
}