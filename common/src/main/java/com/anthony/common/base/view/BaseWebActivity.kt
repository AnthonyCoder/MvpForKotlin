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
    protected abstract val loadUrl: String?
    protected abstract val x5WebView: X5WebView?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFormat(PixelFormat.TRANSLUCENT)
        if (loadUrl != null && x5WebView != null) {
            x5WebView!!.loadUrl(loadUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        x5WebView?.destroy()
    }
}
