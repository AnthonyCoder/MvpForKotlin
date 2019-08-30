package com.anthony.video.business.presenter

import com.anthony.common.base.net.UrlConstant
import com.anthony.common.base.net.client.base.RequestAction
import com.anthony.common.base.net.client.request.child.BaobabRequestClient
import com.anthony.common.base.net.common.bussiness.BasePresenter
import com.anthony.common.base.net.common.observer.AppObserver
import com.anthony.video.bean.VideoListResult
import com.anthony.video.business.contact.VideoListContact

import java.util.HashMap

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
class VideoListPresenter(view: VideoListContact.View) : BasePresenter<VideoListContact.View>(view),
    VideoListContact.Presenter {

    override fun getVideoList(id: Int) {
        val params = HashMap<String, String>()
        params["id"] = id.toString() + ""
        params["udid"] = "d2807c895f0348a180148c9dfa6f2feeac0781b5"
        BaobabRequestClient.client.executeRequest(RequestAction.FormPostAction(params),UrlConstant.POST_CATEGORIES_VIDEO_LIST,object : AppObserver<VideoListResult>() {
            override fun onNext(videoListResult: VideoListResult) {
                videoListResult.itemList?.let {
                    view.setVideoList(it)
                }
            }
        })
    }
}
