package com.anthony.home.business.contact

import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.home.bean.BannerResult
import com.anthony.home.bean.HomeArticleResult
import com.anthony.home.bean.WeChatAuthorResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface HomeContact {
    interface View : BaseView {
        fun onBanner(bannerResults: List<BannerResult.DataBean>)
        fun onWeChatAuthors(weChatAuthorResults: List<WeChatAuthorResult.DataBean>)
        fun onHomeArticles(result: HomeArticleResult)
    }

    interface Presenter {
        fun getBanner()
        fun getWeChatAuthors()
        fun getHomeArticles(page: Int)
    }
}
