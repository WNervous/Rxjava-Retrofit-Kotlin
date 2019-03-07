package com.wys.networkdemo.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class HeaderInterceptor : Interceptor {

    private val PARAMS_KEY_TODAY = "today"
    private val PARAMS_KEY_APP = "app"
    private val PARAMS_KEY_VERSION_NAME = "version"
    private val PARAMS_KEY_VERSION_NUM = "versionNum"
    private val PARAMS_KEY_API_VERSION = "apiVersion"
    private val PARAMS_KEY_API_TIMEZONE = "timezone"
    private val PARAMS_KEY_COOKIE = "cookie"
    private val PARAMS_KEY_COUNTRY = "country"
    private val PARAMS_LOCAL_COOKIE = "serverCookie"
    private val PARAMS_KEY_LANGUAGE = "language"
    private val PARAMS_KEY_PLATFORM = "platform"
    private val API_VERSION = "1"
    private val PLATFORM = "android"
    private var sPackageName: String? = null
    private var sVersionName: String? = null
    private var sVersionNumber: String? = null
//    private var sCountry: String? = null
//    private var sLanguage: String? = null

    private var mCookie: String? = null

    init {
//        sPackageName = MyApplication().getAppPackageName()
//        sVersionName = MyApplication().getVersionName()
//        sVersionNumber = "${MyApplication().getVersionCode()}"
//        sCountry = LocaleUtil.getCountry()
//        sLanguage = LocaleUtil.getLanguage()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        request = builder
//            .addHeader(PARAMS_KEY_COOKIE, getCookie())
//            .addHeader(PARAMS_KEY_APP, sPackageName)
            .addHeader(PARAMS_KEY_API_VERSION, API_VERSION)
//            .addHeader(PARAMS_KEY_COUNTRY, sCountry)
//            .addHeader(PARAMS_KEY_LANGUAGE, sLanguage)
//            .addHeader(PARAMS_KEY_VERSION_NUM, sVersionNumber)
//            .addHeader(PARAMS_KEY_VERSION_NAME, sVersionName)
            .addHeader(PARAMS_KEY_PLATFORM, PLATFORM)
//            .addHeader(PARAMS_KEY_TODAY, sToday)
            .addHeader(PARAMS_KEY_API_TIMEZONE, TimeZone.getDefault().id)
            .addHeader("User-Agent", "Android/$sPackageName/$sVersionName")
            .build()
        return chain.proceed(request)
    }

}