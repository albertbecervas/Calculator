package com.abecerra.calculator.core.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.abecerra.calculator.presentation.ui.about.AboutActivity
import com.abecerra.calculator.presentation.ui.calculator.CalculatorActivity
import org.jetbrains.anko.startActivity

class NavigatorImpl(private var context: Context) : Navigator {

    override fun navigateToCalculator() {
        with(context) {
            startActivity<CalculatorActivity>()
        }
    }

    override fun navigateToAbout() {
        with(context) {
            startActivity<AboutActivity>()
        }
    }

    override fun sendEmail(to: String) {
        with(context) {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:$to")
            startActivity(emailIntent)
        }
    }

}