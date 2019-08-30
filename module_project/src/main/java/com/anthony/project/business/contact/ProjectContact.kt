package com.anthony.project.business.contact

import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.project.bean.ProjectTreeResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface ProjectContact {
    interface View : BaseView {
        fun onProjectTabs(itemList: List<ProjectTreeResult.DataBean>)
    }

    interface Presenter {
        fun getProjectTabs()
    }
}
