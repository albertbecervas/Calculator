package com.abecerra.calculator.core.navigator

interface Navigator {

    fun navigateToCalculator()
    fun navigateToAbout()
    fun sendEmail(to: String)

}