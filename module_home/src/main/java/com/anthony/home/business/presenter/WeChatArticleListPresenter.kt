package com.anthony.home.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.WanAndroidRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.home.bean.WxArticleListResult
import com.anthony.home.business.contact.WeChatArticleListContact

/**
 * 创建时间:2019/8/15
 * 创建人：anthony.wang
 * 功能描述：
 */
class WeChatArticleListPresenter(view: WeChatArticleListContact.View) :
    BasePresenter<WeChatArticleListContact.View>(view), WeChatArticleListContact.Presenter {

    override fun getWeChatArticle(id: Int, page: Int) {
        WanAndroidRequestClient.client.executeRequest(
            RequestAction.FormGetAction(),
            formatUrl(UrlConstant.GET_WXARTICLE_LIST_JSON, "$id", "$page"),
            object : AppObserver<WxArticleListResult>() {
                override fun onNext(wxArticleListResult: WxArticleListResult) {
                    wxArticleListResult.data?.let {
                        view.onWeChatArticleList(it.datas)
                    }
                }
            })
    }
}
