package com.abecerra.calculator.domain.repository

import io.reactivex.Single

interface MathRepository {

    fun calculate(expression: String): Single<Float>

}