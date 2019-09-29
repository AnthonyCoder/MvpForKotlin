package com.anthony.home.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.WanAndroidRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.home.bean.BannerResult
import com.anthony.home.bean.HomeArticleResult
import com.anthony.home.bean.WeChatAuthorResult
import com.anthony.home.business.contact.HomeContact

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class HomePresenter(view: HomeContact.View) : BasePresenter<HomeContact.View>(view), HomeContact.Presenter {

    override fun getBanner() {
        WanAndroidRequestClient.client.executeRequest(
            RequestAction.FormGetAction(),
            UrlConstant.GET_BANNER_JSON,
            object : AppObserver<BannerResult>(view = view,loadTips = "获取Banner信息中...",needBindLifeCycle = false) {
                override fun onNext(bannerResults: BannerResult) {
                    super.onNext(bannerResults)
                    bannerResults.data?.let {
                        view.onBanner(it)
                    }
                }
            })
    }

    override fun getWeChatAuthors() {
        WanAndroidRequestClient.client.executeRequest(
            RequestAction.FormGetAction(),
            UrlConstant.GET_WXARTICLE_CHAPTERS_JSON,
            object : AppObserver<WeChatAuthorResult>() {
                override fun onNext(weChatAuthorResult: WeChatAuthorResult) {
                    super.onNext(weChatAuthorResult)
                    weChatAuthorResult.data?.let {
                        view.onWeChatAuthors(it)
                    }
                }
            })
    }

    override fun getHomeArticles(page: Int) {
        WanAndroidRequestClient.client.executeRequest(
            RequestAction.FormGetAction(),
            formatUrl(UrlConstant.GET_ARTICLE_LIST_JSON, "$page"),
            object : AppObserver<HomeArticleResult>(view = view,needBindLifeCycle = true) {
                override fun onNext(homeArticleResult: HomeArticleResult) {
                    super.onNext(homeArticleResult)
                    view.onHomeArticles(homeArticleResult)
                }
            })
    }
}
