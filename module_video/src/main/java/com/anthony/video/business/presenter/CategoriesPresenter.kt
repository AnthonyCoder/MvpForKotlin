package com.anthony.video.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.BaobabRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.video.bean.GetCategoriesResult
import com.anthony.video.business.contact.CategoriesContact

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class CategoriesPresenter(view: CategoriesContact.View) : BasePresenter<CategoriesContact.View>(view),
    CategoriesContact.Presenter {
    override fun getCategories() {
        BaobabRequestClient.client.executeRequest(RequestAction.FormGetAction(),UrlConstant.GET_CATEGORIES,object : AppObserver<List<GetCategoriesResult>>() {
            override fun onNext(getCategoriesResults: List<GetCategoriesResult>) {
                view.setCategories(getCategoriesResults)
            }
        })
    }
}
