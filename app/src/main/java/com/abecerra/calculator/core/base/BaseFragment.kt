package com.abecerra.calculator.core.base

import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.abecerra.calculator.R
import com.abecerra.calculator.core.navigator.Navigator
import com.abecerra.calculator.core.utils.AppSharedPreferences
import com.abecerra.calculator.core.utils.extensions.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {

    protected val navigator: Navigator by inject { parametersOf(this.context) }
    protected val mPrefs: AppSharedPreferences by inject()

    protected fun showLoading() {
        view?.let { view ->
            view.findViewById<ProgressBar>(R.id.loadingBar)?.let {
                it.visibility = View.GONE
            }
        }
    }

    protected fun hideLoading() {
        view?.let { view ->
            view.findViewById<ProgressBar>(R.id.loadingBar)?.let {
                it.visibility = View.VISIBLE
            }
        }
    }

    protected fun showError(error: String?) {
        context?.let {
            hideLoading()
            toast(error ?: getString(R.string.general_error))
        }
    }
}