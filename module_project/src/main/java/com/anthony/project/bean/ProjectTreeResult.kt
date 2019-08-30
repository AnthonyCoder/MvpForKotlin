package com.anthony.project.bean

import com.anthony.common.base.net.model.BaseResponseModel

/**
 * 创建时间:2019/8/13
 * 创建人：anthony.wang
 * 功能描述：
 */
class ProjectTreeResult : BaseResponseModel() {
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * children : []
         * courseId : 13
         * id : 294
         * name : 完整项目
         * order : 145000
         * parentChapterId : 293
         * userControlSetTop : false
         * visible : 0
         */

        var courseId: Int = 0
        var id: Int = 0
        var name: String? = null
        var order: Int = 0
        var parentChapterId: Int = 0
        var isUserControlSetTop: Boolean = false
        var visible: Int = 0
        var children: List<*>? = null
    }
}
