package com.anthony.project.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.WanAndroidRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.project.bean.ProjectTreeResult
import com.anthony.project.business.contact.ProjectContact

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class ProjectPresenter(view: ProjectContact.View) : BasePresenter<ProjectContact.View>(view), ProjectContact.Presenter {

    override fun getProjectTabs() {
        WanAndroidRequestClient.client.executeRequest(RequestAction.FormGetAction(),UrlConstant.GET_PROJECT_TREE,object : AppObserver<ProjectTreeResult>() {
            override fun onNext(treeResult: ProjectTreeResult) {
                treeResult.data?.let {
                    view.onProjectTabs(it)
                }
            }
        })
    }

}
