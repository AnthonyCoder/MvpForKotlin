package com.anthony.common.base.net.client.base

import com.anthony.common.base.constant.Constant
import com.anthony.common.base.BaseApplication
import com.anthony.common.base.net.api.ApiService
import com.anthony.common.base.net.client.intercept.NetLogInterceptor
import com.anthony.common.base.net.client.ssl.SSLFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.Proxy
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

/**
 * 创建时间:2019/8/29
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract class BaseNetClient {
    protected abstract fun getBaseUrl(): String
    protected abstract fun getCache(): Cache?
    private val HTTP_TIMEOUT_SECONDS:Int = 90//超时时间（s）
    private val READ_TIMEOUT_SECONDS:Int = 90//超时时间（s）
    private val DEFAULT_HTTP_CACHE_LONG_SIZE:Long = 1024 * 1024 * 100//最大缓存大小

    private lateinit var mRetrofit:Retrofit
    protected lateinit var apiService:ApiService
    protected var mGson: Gson = GsonBuilder().disableHtmlEscaping().create()
    protected var defaultCahe: Cache = Cache(File(BaseApplication.application.cacheDir,Constant.HTTP_CACHE),DEFAULT_HTTP_CACHE_LONG_SIZE)

    protected constructor(){
        val okHttpClient = OkHttpClient.Builder().cache(if (getCache() == null) defaultCahe else getCache())
            .sslSocketFactory(SSLFactory.defaultSSLSocketFactory, SSLFactory.x509TrustManager)
            .hostnameVerifier(HostnameVerifier { p0, p1 -> true })//直接设置证书验证结果 若证书验证不通过 设置true即可通过
            .retryOnConnectionFailure(true)//设置失败重连
            .addInterceptor(NetLogInterceptor())//设置网络日志
            .connectTimeout(HTTP_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)//禁用代理使用
            .build()
        mRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(getBaseUrl())
            .build()
        //通过反射获得 T.class
        apiService = mRetrofit.create(ApiService::class.java)
    }

    fun getRequestBodyFromObject(requestData : Any?): RequestBody? {
        requestData?.let {
            return RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                mGson.toJson(it)
            )
        }
        return null
    }

}