package com.abecerra.calculator.data.dto

data class ResponseDto<K>(
    var data: K?,
    var error: ErrorDto?,
    var message: String?,
    var status: String?
) {
    inline fun <O> map(transform: (value: K?) -> O): ResponseDto<O> {
        return ResponseDto(transform(data), error, message, status)
    }
}