package com.anthony.common.base.net.common.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

/**
 * 创建时间:2019/9/29
 * 创建人：anthony.wang
 * 功能描述：
 */
class SubscribeObserver<T> : Observer<ResponseBody> {
    private var appObserver: AppObserver<T>
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
            val bean =  appObserver.getEntityData(json)
            bean ?.let { bean ->
                appObserver.onNext(bean)
            }
            if(bean == null){
                appObserver.onError(Throwable("Response should not be null"))
            }
        } catch (e: Exception) {
            appObserver.onError(e)
        }

    }

    override fun onError(e: Throwable) {
        appObserver.onError(e)
    }

}