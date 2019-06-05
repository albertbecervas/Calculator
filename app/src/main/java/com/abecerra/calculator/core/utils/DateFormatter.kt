package com.abecerra.calculator.core.utils

import com.abecerra.calculator.core.utils.extensions.context
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun formatTransactionToDate(date: String): Date {
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)
        return try {
            dateFormat.parse(date)
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