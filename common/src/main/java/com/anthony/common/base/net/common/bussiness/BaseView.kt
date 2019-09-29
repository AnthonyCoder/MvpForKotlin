package com.anthony.common.base.net.common.bussiness

import android.content.Context
import com.uber.autodispose.AutoDisposeConverter

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：
 */
interface BaseView {
    fun getContext(): Context
    fun showToast(msg: String)
    fun onError(errorMsg: String)
    fun onLoadIng(tip: String)
    fun loadCompleted()
    fun loadError(errorMsg: Any)
    fun <T> bindLifecycle(): AutoDisposeConverter<T>?
}
