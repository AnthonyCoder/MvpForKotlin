package com.anthony.common.base.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.common.util.StatusBarUtil
import com.anthony.common.util.rxlife.RxLifecycleUtils
import com.anthony.common.util.toast.ToastUtils
import com.anthony.common.widgets.loading.dialog.LoadingDialog
import com.uber.autodispose.AutoDisposeConverter

/**
 * 创建时间:2019/8/29
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract open class BaseActivity<P : BasePresenter<*>> : AppCompatActivity(), BaseView {
    private lateinit var mContext: Context
    private var loadingDialog: LoadingDialog? = null
    private var rootView: View? = null
    protected lateinit var mPresenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setStatusBarColor()
        rootView = LayoutInflater.from(mContext).inflate(getLayoutId(), null)
        setContentView(rootView)
        mPresenter = getmPresenter()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        }
        initView()
        initData()
    }


    override fun getContext(): Context = mContext

    override fun showToast(msg: String) {
        ToastUtils.show(msg)
    }

    override fun onError(errorMsg: String) {
        ToastUtils.show(errorMsg)
    }

    override fun onLoadIng(tip: String) {
        loadingDialog = loadingDialog?.let {
            LoadingDialog(mContext)
        }
        loadingDialog?.let {
            it.setLoadingTips(tip)
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    override fun loadCompleted() {
        loadingDialog?.let {
            it.dismiss()
            loadingDialog = null
        }

    }

    override fun loadError(errorMsg: Any) {
        loadingDialog?.let {
            it.dismiss()
            loadingDialog = null
        }
        ToastUtils.show("请求出错")
    }

    override fun <T> bindLifecycle(): AutoDisposeConverter<T> {
        return RxLifecycleUtils.bindLifecycle(this)
    }

    fun routerGo(path: String) {
        ARouter.getInstance().build(path).navigation()
    }
    fun getFragment(path: String): Fragment {
        return ARouter.getInstance().build(path).navigation() as Fragment
    }
    fun setStatusBarColor() {
        StatusBarUtil.setColor(this, resources.getColor(android.R.color.white), 0)
    }

    fun setStatusBarTranslucent(alpha: Int) {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, alpha, null)
    }

    fun setStatusBarTextColor(window: Window, lightStatusBar: Boolean) {
        // 设置状态栏字体颜色 白色与深色
        val decor = window.decorView
        var ui = decor.systemUiVisibility
        ui = ui or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                ui = ui or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                ui = ui and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
        decor.systemUiVisibility = ui
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()
    protected abstract fun initData()

    protected abstract fun getmPresenter(): P
}