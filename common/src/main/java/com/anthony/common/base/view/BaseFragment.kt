package com.anthony.common.base.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.common.util.rxlife.RxLifecycleUtils
import com.anthony.common.util.toast.ToastUtils
import com.anthony.common.widgets.loading.dialog.LoadingDialog
import com.uber.autodispose.AutoDisposeConverter

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract class BaseFragment<P : BasePresenter<*>> : Fragment(), BaseView {
    protected var mActivity: Activity? = null
    private var loadingDialog: LoadingDialog? = null
    private var rootView: View? = null
    protected lateinit var mPresenter: P

    protected abstract val layoutId: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getmPresenter()
        mActivity = activity
        rootView = view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    override fun showToast(msg: String) {
        ToastUtils.show(msg)
    }

    override fun onError(errorMsg: String) {
        ToastUtils.show(errorMsg)
    }

    override fun onLoadIng(tip: String) {
        loadingDialog = loadingDialog?.let {
            mActivity?.let { it1 -> LoadingDialog(it1) }
        }
        loadingDialog?.let {
            it.setLoadingTips(tip)
            if (!it.isShowing) {
                it.show()
            }
        }

    }

    fun setStatusBarTranslucent(alpha: Int) {
        if (mActivity is BaseActivity<*>) {
            (mActivity as BaseActivity<*>).setStatusBarTranslucent(alpha)
        }

    }

    override fun loadCompleted() {
        loadingDialog?.let {
            it.dismiss()
            loadingDialog = null
        }
    }

    override fun loadError(errorMsg: Any) {
        ToastUtils.show("请求出错")
    }

    override fun getContext(): Context = mActivity!!

    override fun <T> bindLifecycle(): AutoDisposeConverter<T> {
        return RxLifecycleUtils.bindLifecycle(this)
    }

    fun routerGo(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    fun getFragment(path: String): Fragment {
        return ARouter.getInstance().build(path).navigation() as Fragment
    }

    protected abstract fun initView()
    protected abstract fun initData()

    protected abstract fun getmPresenter(): P
}
