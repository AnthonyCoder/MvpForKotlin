package com.anthony.common.base.net.client.intercept

import android.text.TextUtils
import com.anthony.common.R
import com.anthony.common.base.constant.Constant
import com.anthony.common.base.BaseApplication
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.orhanobut.logger.Logger
import okhttp3.*
import okio.Buffer

import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：该拦截器主要复制打印请求参数和响应参数等信息 方便开发者调试
 */
class NetLogInterceptor : Interceptor {
    private var isOpenLog = true
    private val UTF8 = Charset.forName("UTF-8")

    fun setOpenLog(openLog: Boolean) {
        isOpenLog = openLog
    }

    constructor() {}
    constructor(isOpenLog: Boolean) {
        this.isOpenLog = isOpenLog
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val method = request.method
        val requestUrl = request.url
        val requestBody = request.body

        if (isOpenLog) {
            if (requestBody != null) {
                val bufferRequest = Buffer()
                requestBody.writeTo(bufferRequest)
                logRetrofitRequest(
                    method,
                    requestUrl?.toString(),
                    bufferRequest.readString(UTF8),
                    request.headers
                )
            } else {
                logRetrofitRequest(
                    method,
                    requestUrl?.toString(),
                    BaseApplication.application.resources.getString(
                        R.string.no_request_body
                    ),
                    request.headers
                )
            }
        }


        val response = chain.proceed(request)

        val responseBody = response.body
        val contentLength = responseBody!!.contentLength()

        if (bodyEncoded(response.headers)) {
            //HTTP (encoded body omitted)
        } else {
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer()

            var charset: Charset? = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8)
                } catch (e: UnsupportedCharsetException) {
                    return response
                }

            }

            if (!isPlaintext(buffer)) {
                return response
            }
            if (contentLength != 0L && isOpenLog) {
                val result = buffer.clone().readString(charset!!)
                logRetrofitResponseSuccess(
                    response.request.url.toString(),
                    result,
                    response.headers
                )
            }

        }
        return response
    }

    private fun logRetrofitRequest(
        method: String,
        url: String?,
        request: String,
        headers: Headers?
    ) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        if (TextUtils.isEmpty(request)) {
            return
        }
        // FORMAT STRING
        val requestText = String.format(
            Constant.NET_REQUEST_STRING,
            method,
            url,
            request
        )
        if (headers != null && headers.size > 0) {
            val sbHeader = StringBuffer()
            for (i in 0 until headers.size) {
                sbHeader.append("${headers.name(i)}:${headers[headers.name(i)]}\n")
            }
            if (sbHeader.toString().trim { it <= ' ' }.isEmpty()) {
                Logger.t(Constant.NET_LOG_TAG).d(
                    String.format(
                        Constant.NET_REQUEST_HEADER, BaseApplication.application.resources.getString(
                            R.string.no_request_header
                        )
                    )
                )
            } else {
                Logger.t(Constant.NET_LOG_TAG).d(String.format(Constant.NET_REQUEST_HEADER, sbHeader.toString()))
            }
        }
        // LOG
        Logger.t(Constant.NET_LOG_TAG).d(requestText)
    }

    private fun logRetrofitResponseSuccess(
        url: String,
        response: String,
        headers: Headers?
    ) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        if (TextUtils.isEmpty(response)) {
            return
        }
        // FORMAT STRING
        val responseText = String.format(
            Constant.NET_RESPONSE_SUCESS_STRING,
            url,
            response
        )
        if (headers != null && headers.size > 0) {
            val sbHeader = StringBuffer()
            for (i in 0 until headers.size) {
                sbHeader.append("${headers.name(i)}:${headers[headers.name(i)]}\n")
            }
            if (sbHeader.toString().trim { it <= ' ' }.isEmpty()) {
                Logger.t(Constant.NET_LOG_TAG).d(
                    String.format(
                        Constant.NET_RESPONSE_HEADER, BaseApplication.application.resources.getString(
                            R.string.no_response_header
                        )
                    )
                )
            } else {
                Logger.t(Constant.NET_LOG_TAG).d(String.format(Constant.NET_RESPONSE_HEADER, sbHeader.toString()))
            }

        }
        // 打印json日志
        if (validate(responseText)) {
            Logger.t(Constant.NET_LOG_TAG).json(responseText)
        } else {
            Logger.t(Constant.NET_LOG_TAG).d(responseText)
        }

    }

    private fun isPlaintext(buffer: Buffer): Boolean {
        try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            return true
        } catch (e: EOFException) {
            return false
        }

    }

    private fun validate(jsonStr: String): Boolean {
        val jsonElement: JsonElement?
        try {
            jsonElement = JsonParser().parse(jsonStr)
        } catch (e: Exception) {
            return false
        }

        if (jsonElement == null) {
            return false
        }
        return jsonElement.isJsonObject
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
    }

}