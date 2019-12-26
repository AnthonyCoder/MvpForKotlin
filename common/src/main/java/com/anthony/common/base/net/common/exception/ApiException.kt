package com.anthony.common.base.net.common.exception

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：
 */
class ApiException(throwable: Throwable, private val code: Int) : Exception(throwable) {
    //用于展示的异常信息
    var displayMessage: String? = null
    init {
        if (code == ExceptionEngine.ERROR.UNKNOWN) {
            displayMessage = throwable.message
        }

    }
}
