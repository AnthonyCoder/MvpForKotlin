package com.anthony.project.business.contact

import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.project.bean.ProjectListResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface ProjectPageContact {
    interface View : BaseView {
        fun projectList(modelList: List<ProjectListResult.DataBean.DatasBean>)
    }

    interface Presenter {
        fun getProject(id: Int, page: Int)
    }
}
