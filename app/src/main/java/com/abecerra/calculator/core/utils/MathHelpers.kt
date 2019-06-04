package com.abecerra.calculator.core.utils

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

object Helpers {
    var division = "&divide;"
    var inverseSin = "sin<sup>-1</sup>"
    var inverseCos = "cos<sup>-1</sup>"
    var inverseTan = "tan<sup>-1</sup>"
    var exponential = "e<sup>x</sup>"
    var tenPowerX = "10<sup>x</sup>"
    var cubeSquare = "3&radic;"
    var cubeRoot = "x<sup>3</sup>"
    var yPowerX = "Y<sup>x</sup>"
    var squareRoot = "&radic;"
    var xSquare = "x<sup>2</sup>"
    var pi = "&pi;"
    fun displayErrorMessage(context: Context) {
        Toast.makeText(context, "Input field must not be zero", Toast.LENGTH_LONG).show()
    }

    fun isZero(input: EditText): Boolean {
        return java.lang.Double.parseDouble(input.text.toString()) == 0.0
    }

    fun getTopicId(bundle: Bundle?, inputValue: String): Int {
        var id = 0
        if (bundle != null) {
            id = bundle.getInt(inputValue)
        }
        return id
    }
}