package com.abecerra.calculator.core.utils.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

enum class DataState { LOADING, SUCCESS, ERROR }

data class Data<out T>(val dataState: DataState, val data: T? = null, val message: String? = null) :
    Serializable

fun <T> Single<T>.subscribe(
    onSubscribe: () -> Unit = {},
    success: (data: T) -> Unit,
    error: (throwable: Throwable) -> Unit,
    compositeDisposable: CompositeDisposable
) {

    subscribeOn(Schedulers.newThread())
        .doOnSubscribe { onSubscribe.invoke() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            success.invoke(it)
        }, {
            error.invoke(it)
        }).addTo(compositeDisposable)

}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<Any>> LifecycleOwner.observe(liveData: L, body: () -> Unit) {

}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeChanges(liveData: L, body: () -> Unit) {
    liveData.observe(this, Observer { body() })
}

fun <K> MutableLiveData<Data<K>>.showError(message: String?) {
    postValue(Data(dataState = DataState.ERROR, message = message))
}

fun <K> MutableLiveData<Data<K>>.showError(message: Int) {
    postValue(Data(dataState = DataState.ERROR, message = context.getString(message)))
}

fun <K> MutableLiveData<Data<K>>.showError2(error: Throwable) {
    postValue(Data(dataState = DataState.ERROR, message = error.message))
}

fun <K> MutableLiveData<Data<K>>.showLoading() {
    postValue(Data(dataState = DataState.LOADING))
}

fun <K> MutableLiveData<Data<K>>.updateData(data: K?, message: String? = null) {
    postValue(
        Data(
            dataState = DataState.SUCCESS,
            data = data,
            message = message
        )
    )
}

