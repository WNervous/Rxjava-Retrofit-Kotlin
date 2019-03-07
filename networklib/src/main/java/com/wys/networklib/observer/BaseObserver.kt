package com.wys.networklib.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        onFailure(e)
    }

    abstract fun onSuccess(t: T)

    abstract fun onFailure(e: Throwable)
}