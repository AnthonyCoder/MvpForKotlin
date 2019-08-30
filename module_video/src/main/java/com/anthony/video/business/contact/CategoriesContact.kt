package com.anthony.video.business.contact

import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.video.bean.GetCategoriesResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface CategoriesContact {
    interface View : BaseView {
        fun setCategories(dataList: List<GetCategoriesResult>)
    }

    interface Presenter {
        fun getCategories()
    }
}
