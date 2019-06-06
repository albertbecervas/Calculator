package com.abecerra.calculator.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun formatTransactionToDate(date: String): Date {
        val dateFormat = SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH)
        return try {
            dateFormat.parse(date.replace(" GMT+0000", ""))
        } catch (e: Exception) {
            Date()
        }
    }

    fun formatTransactionFromDate(date: Date): String {

        val output = "dd MMMM yyyy"

        val outputDateFormat = SimpleDateFormat(output, Locale.ENGLISH)

        return try {
            outputDateFormat.format(date)
        } catch (e: Exception) {
            "-"
        }

    }

}