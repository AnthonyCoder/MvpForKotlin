package com.anthony.video.bean

import com.anthony.common.base.net.model.BaseResponseModel

/**
 * 创建时间:2019/8/21
 * 创建人：anthony.wang
 * 功能描述：
 */
class GetCategoriesResult : BaseResponseModel() {
    /**
     * id : 14
     * name : 广告
     * alias : null
     * description : 为广告人的精彩创意点赞
     * bgPicture : http://img.kaiyanapp.com/57472e13fd2b6c9655c8a600597daf4d.png?imageMogr2/quality/60/format/jpg
     * bgColor :
     * headerImage : http://img.kaiyanapp.com/fc228d16638214b9803f46aabb4f75e0.png
     * defaultAuthorId : 2162
     * tagId : 16
     */

    var id: Int = 0
    var name: String? = null
    var alias: Any? = null
    var description: String? = null
    var bgPicture: String? = null
    var bgColor: String? = null
    var headerImage: String? = null
    var defaultAuthorId: Int = 0
    var tagId: Int = 0
}
