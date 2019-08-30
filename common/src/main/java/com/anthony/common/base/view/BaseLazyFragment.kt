package com.anthony.common.base.view

import android.os.Bundle
import android.view.View
import com.anthony.common.base.net.common.bussiness.BasePresenter


/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
abstract class BaseLazyFragment<P : BasePresenter<*>> : BaseFragment<P>() {

    /**
     * Fragment 中的 View 是否创建完成
     */
    protected var isViewCreated: Boolean = false

    /**
     * Fragment 是否对用户可见
     */
    protected var isUserVisible: Boolean = false

    /**
     * Fragment 左右切换时，只在第一次显示时请求数据
     */
    protected var isFirstLoad = true

    /**
     * Fragment 中创建完成的回调方法
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        lazyLoad()
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isUserVisible = true
            onVisible()
        } else {
            isUserVisible = false
            onInvisible()
        }
    }

    private fun onInvisible() {

    }

    protected fun onVisible() {
        lazyLoad()
    }


    /**
     * (1) isViewCreated 参数在系统调用 onViewCreated 时设置为 true,这时onCreateView方法已调用完毕(一般我们在这方法
     * 里执行findviewbyid等方法),确保 loadData()方法不会报空指针异常。
     *
     *
     * (2) isUserVisible 参数在 fragment 可见时通过系统回调 setUserVisibileHint 方法设置为true,不可见时为false，
     * 这是 fragment 实现懒加载的关键。
     *
     *
     * (3) isFirstLoad 确保 ViewPager 来回切换时 TabFragment 的 loadData()方法不会被重复调用，loadData()在该
     * Fragment 的整个生命周期只调用一次,第一次调用 loadData()方法后马上执行 isFirst = false。
     */
    private fun lazyLoad() {
        /**
         * 这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
         * 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
         */
        if (isViewCreated && isUserVisible && isFirstLoad) {
            lazyLoadData()
            isFirstLoad = false
        }
    }

    /**
     * 子类实现加载数据
     */
    protected abstract fun lazyLoadData()
}
