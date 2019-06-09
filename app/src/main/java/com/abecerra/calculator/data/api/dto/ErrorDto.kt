package com.abecerra.calculator.data.api.dto

import com.abecerra.calculator.core.utils.extensions.beautify


data class ErrorDto(
    val type: String = "",
    val description: String = "",
    val fields: List<ErrorFieldsDto> = emptyList()
) {
    override fun toString(): String {
        val stringBuilder = StringBuilder()

        if (fields.isNotEmpty()) {
            stringBuilder.appendln(description)
            fields.forEach {
                val field = it.field.beautify()
                stringBuilder.appendln("$field: ${it.description}")
            }
        } else {
            stringBuilder.append(description)
        }

        return stringBuilder.toString()
    }
}

data class ErrorFieldsDto(
    val field: String = "",
    val description: String = ""
)