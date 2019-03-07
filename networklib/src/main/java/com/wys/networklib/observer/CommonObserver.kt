package com.wys.networklib.observer

import com.wys.networklib.bean.CommonResponse

abstract class CommonObserver<T> : BaseObserver<CommonResponse<T>>() {
    override fun onNext(t: CommonResponse<T>) {
       if (t.isSuccess()) onSuccess(t) else onFailure(Throwable(t.errorMsg))
    }
}