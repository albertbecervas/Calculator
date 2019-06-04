package com.abecerra.calculator

import com.abecerra.calculator.core.math.Calculator
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        try {
            print(Calculator.evaluate("sin()"))
        } catch (e: Exception) {
            print(e.message)
        }
    }
}
