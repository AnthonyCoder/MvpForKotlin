package com.anthony.gank.business.presenter


import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.GankRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.gank.bean.GankImageResult
import com.anthony.gank.business.contact.GankContact

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class GankPresenter(view: GankContact.View) : BasePresenter<GankContact.View>(view), GankContact.Presenter {

    override fun getGankData(page: Int) {
        GankRequestClient.client.executeRequest(
            RequestAction.FormGetAction(),
            formatUrl(UrlConstant.GET_IMAGE_LIST, "16", "$page"),
            object : AppObserver<GankImageResult>() {
                override fun onNext(gankImageResult: GankImageResult) {
                    if (gankImageResult.results != null) {
                        if (gankImageResult.results!!.isNotEmpty()) {
                            for (bean in gankImageResult.results!!) {
                                bean.with = 550
                                bean.height = (Math.random() * 101).toInt() + 600
                            }
                        }
                        view.setGankData(gankImageResult.results!!)
                    }

                }

            })


    }
}
