package com.anthony.common.base.net.common.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException

import java.net.ConnectException
import java.text.ParseException

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：
 */
object ExceptionEngine {

    //对应HTTP的状态码
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403
    private val NOT_FOUND = 404
    private val REQUEST_TIMEOUT = 408
    private val INTERNAL_SERVER_ERROR = 500
    private val BAD_GATEWAY = 502
    private val SERVICE_UNAVAILABLE = 503
    private val GATEWAY_TIMEOUT = 504

    fun handleException(e: Throwable): ApiException {
        val ex: ApiException
        if (e is HttpException) {             //HTTP错误
            ex = ApiException(e, ERROR.HTTP_ERROR)
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.displayMessage =
                    "网络错误"  //均视为网络错误
                else -> ex.displayMessage = "网络错误"
            }
            return ex
        } else if (e is ServerException) {    //服务器返回的错误
            ex = ApiException(e, e.errCode)
            ex.displayMessage = e.message
            return ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            ex = ApiException(e, ERROR.PARSE_ERROR)
            ex.displayMessage = "解析错误"            //均视为解析错误
            return ex
        } else if (e is ConnectException) {
            ex = ApiException(e, ERROR.NETWORD_ERROR)
            ex.displayMessage = "连接失败"  //均视为网络错误
            return ex
        } else {
            ex = ApiException(e, ERROR.UNKNOWN)
            ex.displayMessage = "未知错误"          //未知错误
            return ex
        }
    }

    object ERROR {
        const val HTTP_ERROR = 0x11
        const val PARSE_ERROR = 0x12
        const val NETWORD_ERROR = 0x13
        const val UNKNOWN = 0x14
    }
}
