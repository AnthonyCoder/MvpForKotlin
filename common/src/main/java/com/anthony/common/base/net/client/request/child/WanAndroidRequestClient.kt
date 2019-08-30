package com.anthony.common.base.net.client.request.child

import com.anthony.common.base.net.Protocols
import com.anthony.common.base.net.client.request.RequestClient
import okhttp3.Cache

/**
 * 创建时间:2019/8/16
 * 创建人：anthony.wang
 * 功能描述：
 */
class WanAndroidRequestClient : RequestClient() {
    override fun getCache(): Cache?  = null
    override fun getBaseUrl(): String = Protocols.WAN_ANDROID_BASE_RELEASE_API_URL

    private object SingleHolder {
        val instance = WanAndroidRequestClient()
    }

    companion object {
        val client = SingleHolder.instance
    }
}
