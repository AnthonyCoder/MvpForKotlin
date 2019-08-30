package com.anthony.home.bean

import com.anthony.common.base.net.model.BaseResponseModel

class MessageLogResult : BaseResponseModel() {
    /**
     * IsSuccess : true
     * Data : [{"Id":3,"Title":"Updated tools successfully","Detail":"Go to check the updated tools.","PicUrl":null,"RootType":0,"Type":8,"FriendId":null,"GuidLineId":null,"Readed":false,"CreateTime":"2018-08-03T03:00:34.404Z"},{"Id":2,"Title":"Updated tools successfully","Detail":"Go to check the updated tools.","PicUrl":null,"RootType":0,"Type":8,"FriendId":null,"GuidLineId":null,"Readed":false,"CreateTime":"2018-08-03T02:59:11.829Z"},{"Id":1,"Title":"Updated tools successfully","Detail":"Go to check the updated tools.","PicUrl":null,"RootType":0,"Type":8,"FriendId":null,"GuidLineId":null,"Readed":true,"CreateTime":"2018-08-03T02:58:49.009Z"}]
     */

    var data: List<DataBean>? = null

    class DataBean {
        /**
         * Id : 3
         * Title : Updated tools successfully
         * Detail : Go to check the updated tools.
         * PicUrl : null
         * RootType : 0
         * Type : 8
         * FriendId : null
         * GuidLineId : null
         * Readed : false
         * CreateTime : 2018-08-03T03:00:34.404Z
         */

        var id: Long = 0
        var title: String? = null
        var detail: String? = null
        var picUrl: String? = null
        var rootType: Int = 0
        var type: Int = 0
        var friendId: String? = null
        var guidLineId: String? = null
        var clinicalTrialId: String? = null
        var isReaded = true
        var createTime: String? = null
    }
}
