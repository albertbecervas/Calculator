package com.abecerra.calculator.core.base

import io.reactivex.Single

abstract class BaseDao<K> {

    abstract fun getItemById(id: String): Single<K>
    abstract fun addOrUpdate(item: K)
    abstract fun addOrUpdate(items: List<K>)
    fun delete() {}

}