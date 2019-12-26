package com.anthony.common.base.net.common.bussiness

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
open class BasePresenter<V : BaseView>(protected var view: V) {
    protected fun formatUrl(needFormatUrl: String, vararg params: Any?) =  String.format(needFormatUrl, *params)
}
