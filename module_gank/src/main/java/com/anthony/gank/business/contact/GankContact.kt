package com.anthony.gank.business.contact


import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.gank.bean.GankImageResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface GankContact {
    interface View : BaseView {
        fun setGankData(resultsBeanList: List<GankImageResult.ResultsBean>)
    }
    interface Presenter {
        fun getGankData(page: Int)
    }
}
