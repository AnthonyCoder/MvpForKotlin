package com.anthony.video.business.contact

import com.anthony.common.base.net.common.bussiness.BaseView
import com.anthony.video.bean.VideoListResult

/**
 * 创建时间:2019/8/9
 * 创建人：anthony.wang
 * 功能描述：
 */
interface VideoListContact {
    interface View : BaseView {
        fun setVideoList(dataList: List<VideoListResult.ItemListBean>)
    }

    interface Presenter {
        fun getVideoList(id: Int)
    }
}
