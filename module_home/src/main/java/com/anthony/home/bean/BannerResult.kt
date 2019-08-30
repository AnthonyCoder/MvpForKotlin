package com.anthony.home.bean

import com.anthony.common.base.net.model.BaseResponseModel

class BannerResult : BaseResponseModel() {

    var data: List<DataBean>? = null

    class DataBean {
        /**
         * desc : Android架构师成长路线图
         * id : 26
         * imagePath : https://wanandroid.com/blogimgs/4f66c08e-d8b6-470d-9c8c-eeed9dbfb2a3.png
         * isVisible : 1
         * order : 1
         * title : Android架构师成长路线图
         * type : 0
         * url : https://mp.weixin.qq.com/s?__biz=MzU2NTcwMTU0OQ==&amp;mid=2247485212&amp;idx=2&amp;sn=0299811734eea94ec4e186aa21bae175&amp;chksm=fcb6f9decbc170c8b2033db6d3919b0762b0b4c80c80f7afdb39353e228e8df4e2361102ff03&amp;token=1498464891&amp;lang=zh_CN#rd
         */

        var desc: String? = null
        var id: Int = 0
        var imagePath: String? = null
        var isVisible: Int = 0
        var order: Int = 0
        var title: String? = null
        var type: Int = 0
        var url: String? = null
    }
}
