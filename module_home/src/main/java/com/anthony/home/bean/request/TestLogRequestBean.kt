package com.anthony.home.bean.request

import com.anthony.common.base.net.model.BaseRequesModel

/**
 * 创建时间:2019/8/27
 * 创建人：anthony.wang
 * 功能描述：
 */
class TestLogRequestBean : BaseRequesModel() {
    var accessToken: String? = null
    var cultureCode: String? = null
    var device: DeviceKeyModel? = null
    var appName: String? = null

    class DeviceKeyModel {
        /**
         * SystemName : String
         * SystemVersion : String
         * PlatformName : String
         * DeviceUnique : String
         */

        var systemName: String? = null
        var systemVersion: String? = null
        var platformName: String? = null
        var deviceUnique: String? = null
    }
}
