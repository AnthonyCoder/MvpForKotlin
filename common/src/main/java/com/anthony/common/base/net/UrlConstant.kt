package com.anthony.common.base.net

/**
 * 创建时间:2019/8/6
 * 创建人：anthony.wang
 * 功能描述：
 */
object UrlConstant {

    /**
     * WAN Android API
     */
    const val WEATHER_CITY_LIST = "simpleWeather/cityList"
    const val GET_BANNER_JSON = "banner/json"//获取Banner接口
    const val GET_WXARTICLE_CHAPTERS_JSON = "wxarticle/chapters/json"// 获取公众号列表
    const val GET_WXARTICLE_LIST_JSON = "wxarticle/list/%s/%s/json"


    const val GET_ARTICLE_LIST_JSON = "article/list/%s/json"//获取首页文章列表 +cid 获取体系下分级下文章
    const val POST_QUERY_ARTICLE = "article/query/%s/json"//搜索文章

    const val GET_PROJECT = "project/list/%s/json"//Project 指定栏目下的列表
    const val GET_PROJECT_TREE = "project/tree/json"//Project 栏目分类

    const val POST_COLLECT_APP = "lg/collect/%s/json"//收藏站内文章
    const val POST_COLLECT_OTHER = "lg/collect/add/json"//收藏站外文章


    const val GET_HOT_KEY = "hotkey/json"// 获取搜索热词
    const val GET_TREE = "tree/json"//获取体系列表

    const val GET_COLLECT_LIST = "lg/collect/list/%s/json"//获取收藏文章列表

    /**
     * Gank API
     */
    const val GET_IMAGE_LIST = "api/data/福利/%s/%s"//获取福利图片


    /***
     * 开眼 API
     */
    const val GET_CATEGORIES = "v4/categories"//获取视频分类
    const val POST_CATEGORIES_VIDEO_LIST = "v4/categories/videoList"//获取视频分类


    /**
     * JIBO API
     */
    const val POST_MESSAGE_LOG = "health/message/list"
}
