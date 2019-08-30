package com.anthony.common.base.net.api

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * 创建时间:2019/8/29
 * 创建人：anthony.wang
 * 功能描述：
 */
@JvmSuppressWildcards
interface ApiService {
    @GET("{url}")
    fun executeGet(
        @Path(value = "url", encoded = true) url: String,
        @QueryMap maps: Map<String, Any>
    ): Observable<ResponseBody>


    @GET("{url}")
    fun executeGet(
        @Path(value = "url", encoded = true) url: String,
        @Body requestBody: RequestBody
    ): Observable<ResponseBody>

    @GET("{url}")
    fun executeGet(
        @Path(value = "url", encoded = true) url: String
    ): Observable<ResponseBody>


    @POST("{url}")
    fun executePost(
        @Path(value = "url", encoded = true) url: String
    ): Observable<ResponseBody>

    @POST("{url}")
    fun executePost(
        @Path(value = "url", encoded = true) url: String,
        @QueryMap maps: Map<String, Any>
    ): Observable<ResponseBody>

    @POST("{url}")
    fun executePost(
        @Path(
            value = "url",
            encoded = true
        ) url: String, @Body requestBody: RequestBody
    ): Observable<ResponseBody>

    @GET("{url}")
    fun executeGetWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @QueryMap maps: Map<String, Any>
    ): Observable<ResponseBody>

    @GET("{url}")
    fun executeGetWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @Body requestBody: RequestBody
    ): Observable<ResponseBody>

    @GET("{url}")
    fun executeGetWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String
    ): Observable<ResponseBody>


    @POST("{url}")
    fun executePostWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @FieldMap maps: Map<String, Any>
    ): Observable<ResponseBody>

    @POST("{url}")
    fun executePostWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String,
        @Body requestBody: RequestBody
    ): Observable<ResponseBody>


    @POST("{url}")
    fun executePostWithHeader(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "url", encoded = true) url: String
    ): Observable<ResponseBody>

}