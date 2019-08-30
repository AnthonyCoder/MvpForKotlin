package com.anthony.project.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.WanAndroidRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.project.bean.ProjectListResult
import com.anthony.project.business.contact.ProjectPageContact

import java.util.HashMap

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class ProjectPagePresenter(view: ProjectPageContact.View) : BasePresenter<ProjectPageContact.View>(view),
    ProjectPageContact.Presenter {

    override fun getProject(id: Int, page: Int) {
        val params = HashMap<String, Int>()
        params["cid"] = id
        WanAndroidRequestClient.client.executeRequest(RequestAction.FormGetAction(params),formatUrl(UrlConstant.GET_PROJECT, "$page"),
            object : AppObserver<ProjectListResult>() {
                override fun onNext(treeResult: ProjectListResult) {
                    treeResult.data?.let {
                        it.datas?.let { it1 -> view.projectList(it1) }
                    }

                }
            })
    }
}
