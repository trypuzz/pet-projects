package com.example.triviaquiz.util.apisingleobserver

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ApiSingleObserver<T> constructor(private val compositeDisposable: CompositeDisposable) :
    SingleObserver<T> {

    override fun onError(e: Throwable) {
        onError(
            Error(
                throwable = e,
                message = e.localizedMessage ?: ""
            )
        )
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onSuccess(t: T) {
        onResult(t)
    }

    abstract fun onResult(data: T)
    abstract fun onError(e: Error)

}