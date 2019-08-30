package com.anthony.common.base.net.common.observer

import com.anthony.common.R
import com.anthony.common.base.constant.Constant
import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.common.base.net.common.exception.ApiException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import io.reactivex.disposables.Disposable

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：用来处理订阅者生命周期中的一些公共逻辑
 */
abstract class AppObserver<T> : BaseObserver<T> {
    private var view: BaseView? = null
    private val TYPE_CLASS_ENTITY_HEAD = "class"
    private val TYPE_LIST_ENTITY_HEAD = "java.util.List"
    private val TYPE_ARRAY_LIST_ENTITY_HEAD = "java.util.ArrayList"


    constructor(view: BaseView) {
        this.view = view
        onload(null)
    }

    constructor(view: BaseView, loadTips: String) {
        this.view = view
        onload(loadTips)
    }

    constructor()

    private fun onload(loadTips: String?) {
        view?.onLoadIng(loadTips ?: view!!.getContext().getString(R.string.loading))
    }

    override fun onError(ex: ApiException) {
        Logger.t(Constant.NET_LOG_TAG).e(String.format(Constant.NET_EXCEPTION_STRING, ex.displayMessage))
        view?.loadError(ex)

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onComplete() {
        view?.loadCompleted()
    }

    fun getEntityData(json: String): T {//这里是处理Json转换成实例对象或者集合的方法
        val gson = Gson()
        var entityData: T? = null
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        val type = parameterizedType.actualTypeArguments[0]
        if (type.toString().startsWith(TYPE_CLASS_ENTITY_HEAD)) {//首先判断是否是对象形式的data
            val tClass = type as Class<T>
            entityData = gson.fromJson(json, tClass)
        } else if (type.toString().startsWith(TYPE_LIST_ENTITY_HEAD) || type.toString().startsWith(
                TYPE_ARRAY_LIST_ENTITY_HEAD
            )
        ) {//如果是集合形式的data会走这里的异常 直接转集合就行了
            entityData = gson.fromJson<T>(json, TypeToken.get(type).type)
        }
        return entityData!!
    }
}
