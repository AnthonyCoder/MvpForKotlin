package com.anthony.common.base.net.client.request

import com.anthony.common.base.net.client.base.BaseNetClient
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.common.observer.AppObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/**
 * 创建时间:2019/8/29
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract class RequestClient : BaseNetClient() {
    fun <T> executeRequest(action: RequestAction, url: String, observer: AppObserver<T>): Observable<ResponseBody>? {
        var requestObservable: Observable<ResponseBody>? = null
        var responseBodyObserver: Observer<ResponseBody> = object : Observer<ResponseBody> {
            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(responseBody: ResponseBody) {
                try {
                    val json = responseBody.string()
                    observer.onNext(observer.getEntityData(json))
                } catch (e: Exception) {
                    observer.onError(e)
                }
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
        when (action) {
            is RequestAction.FormGetAction -> {
                requestObservable = if (action.params == null) {
                    apiService.executeGet(url)
                } else {
                    apiService.executeGet(url, action.params)
                }
            }
            is RequestAction.FormPostAction -> {
                requestObservable = if (action.params == null) {
                    apiService.executePost(url)
                } else {
                    apiService.executePost(url, action.params)
                }
            }
            is RequestAction.FormGetWithHeaderAction -> {
                requestObservable = if (action.params == null) {
                    apiService.executeGetWithHeader(action.headerMap, url)
                } else {
                    apiService.executeGetWithHeader(action.headerMap, url, action.params)
                }
            }
            is RequestAction.FormPostWithHeaderAction -> {
                requestObservable = if (action.params == null) {
                    apiService.executePostWithHeader(action.headerMap, url)
                } else {
                    apiService.executePostWithHeader(action.headerMap, url, action.params)
                }
            }
            is RequestAction.RawGetAction<*> -> {
                requestObservable = if (action.requestModel == null) {
                    apiService.executeGet(url)
                } else {
                    apiService.executeGet(url, getRequestBodyFromObject(action.requestModel))
                }
            }
            is RequestAction.RawPostAction<*> -> {
                requestObservable = if (action.requestModel == null) {
                    apiService.executePost(url)
                } else {
                    apiService.executePost(url, getRequestBodyFromObject(action.requestModel))
                }
            }
            is RequestAction.RawGetWithHeaderAction<*> -> {
                requestObservable = if (action.requestModel == null) {
                    apiService.executeGetWithHeader(action.headerMap, url)
                } else {
                    apiService.executeGetWithHeader(
                        action.headerMap,
                        url,
                        getRequestBodyFromObject(action.requestModel)
                    )
                }
            }
            is RequestAction.RawPostWithHeaderAction<*> -> {
                requestObservable = if (action.requestModel == null) {
                    apiService.executePostWithHeader(action.headerMap, url)
                } else {
                    apiService.executePostWithHeader(
                        action.headerMap,
                        url,
                        getRequestBodyFromObject(action.requestModel)
                    )
                }
            }
        }
        requestObservable?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(responseBodyObserver)
        return requestObservable
    }
}
