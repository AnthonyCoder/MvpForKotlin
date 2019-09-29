package com.anthony.common.base.net.common.observer.SubscribeObserver

import com.anthony.common.base.net.common.observer.AppObserver
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

/**
 * 创建时间:2019/9/29
 * 创建人：anthony.wang
 * 功能描述：
 */
class SubscribeObserver<T> : Observer<ResponseBody> {
    private lateinit var appObserver: AppObserver<T>
    constructor(appObserver: AppObserver<T>){
        this.appObserver = appObserver
    }
    override fun onComplete() {
        appObserver.onComplete()
    }

    override fun onSubscribe(d: Disposable) {
        appObserver.onSubscribe(d)
    }

    override fun onNext(responseBody: ResponseBody) {
        try {
            val json = responseBody.string()
            appObserver.onNext(appObserver.getEntityData(json))
        } catch (e: Exception) {
            appObserver.onError(e)
        }

    }

    override fun onError(e: Throwable) {
        appObserver.onError(e)
    }

}