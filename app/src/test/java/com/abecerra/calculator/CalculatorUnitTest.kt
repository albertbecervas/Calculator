package com.abecerra.calculator

import com.abecerra.calculator.core.math.Calculator
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {
    @Test
    fun basicOperations_isCorrect() {
        try {
            assertEquals(4.0, Calculator.evaluate("2+2"))
            assertEquals(0.0, Calculator.evaluate("2-2"))
            assertEquals(4.0, Calculator.evaluate("2*2"))
            assertEquals(1.0, Calculator.evaluate("2/2"))
        } catch (e: Exception) {
            print(e.message)
        }
    }

    @Test
    fun advancedOperations_isCorrect() {
        try {
            assertEquals(12.0, Calculator.evaluate("(2+2)*3"))
            assertEquals(2.0, Calculator.evaluate("sqrt(4)"))
            assertEquals(1.0, Calculator.evaluate("cos(0)"))
            assertEquals(0.0, Calculator.evaluate("sin(0)"))
            assertEquals(0.0, Calculator.evaluate("tan(0)"))
            assertEquals(4.0, Calculator.evaluate("2^2"))
        } catch (e: Exception) {
            print(e.message)
        }
    }

}
