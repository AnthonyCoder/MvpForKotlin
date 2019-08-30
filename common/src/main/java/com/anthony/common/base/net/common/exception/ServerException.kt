package com.anthony.common.base.net.common.exception

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：
 */
class ServerException(errCode: Int, msg: String) : Exception(msg) {

    /**
     * 错误的请求KEY
     */
    val SERVER_ERROR_ERROR_KEY = 10001

    /**
     * 该KEY无请求权限
     */
    val SERVER_ERROR_KEY_NO_PERMISSION = 10002

    /**
     * KEY过期
     */
    val SERVER_ERROR_KEY_OUT_TIME = 10003

    var errCode = 0

    init {
        this.errCode = errCode
    }
}
