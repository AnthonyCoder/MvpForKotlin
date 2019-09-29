package com.anthony.common.base.view

import android.graphics.PixelFormat
import android.os.Bundle
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.widgets.webview.X5WebView

/**
 * 创建时间:2019/8/14
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract class BaseWebActivity<P : BasePresenter<*>> : BaseActivity<P>() {
    protected abstract fun getLoadUrl(): String?
    protected abstract fun getX5WebView(): X5WebView?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.TRANSLUCENT)
        if (getLoadUrl() != null && getX5WebView() != null) {
            getX5WebView()!!.loadUrl(getLoadUrl())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getX5WebView()?.destroy()
    }
}
