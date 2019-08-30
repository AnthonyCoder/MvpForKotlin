package com.anthony.common.base.net.client.base

import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.common.base.net.model.BaseRequesModel

/**
 * 创建时间:2019/8/29
 * 创建人：anthony.wang
 * 功能描述：请求参数密闭类
 */
sealed class RequestAction {
    class FormGetAction(val params:Map<String,Any>? = null):RequestAction()
    class FormGetWithHeaderAction(val headerMap:Map<String,String>,val params:Map<String,Any>? = null):RequestAction()
    class FormPostAction(val params:Map<String,Any>? = null):RequestAction()
    class FormPostWithHeaderAction(val headerMap:Map<String,String>,val params:Map<String,Any>? = null):RequestAction()

    class RawGetAction<T:BaseRequesModel>(val requestModel:T? = null):RequestAction()
    class RawGetWithHeaderAction<T:BaseRequesModel>(val headerMap:Map<String,String>,val requestModel:T? = null):RequestAction()
    class RawPostAction<T:BaseRequesModel>(val requestModel:T? = null):RequestAction()
    class RawPostWithHeaderAction<T:BaseRequesModel>(val headerMap:Map<String,String>,val requestModel:T? = null):RequestAction()

}