package com.wys.networklib

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApi(builder: Builder) {
    private var mBaseUrl: String?
    private var retrofit: Retrofit
    private var okHttpClient: OkHttpClient

    init {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.retryOnConnectionFailure(true)
            .connectTimeout(builder.connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(builder.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(builder.writeTimeOut, TimeUnit.SECONDS)

        if (builder.headerInterceptor != null) okHttpBuilder.addInterceptor(builder.headerInterceptor!!)
        if (builder.paramsInterceptor != null) okHttpBuilder.addInterceptor(builder.paramsInterceptor!!)
        if (builder.networkInterceptor != null) okHttpBuilder.addNetworkInterceptor(builder.networkInterceptor!!)
        if (builder.httpLogInterceptor != null) okHttpBuilder.addInterceptor(builder.httpLogInterceptor!!)

        okHttpClient = okHttpBuilder.build()

        mBaseUrl = if (builder.isRelease) builder.baseReleaseUrl!! else builder.baseDebugUrl!!
        retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl!!)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    class Builder {
        companion object {
            const val DEFAULT_TIME_OUT = 15
        }

        var networkInterceptor: Interceptor? = null
        var httpLogInterceptor: Interceptor? = null
        var headerInterceptor: Interceptor? = null
        var paramsInterceptor: Interceptor? = null

        var baseReleaseUrl: String? = null
        var baseDebugUrl: String? = null

        var readTimeOut = DEFAULT_TIME_OUT.toLong()
        var writeTimeOut = DEFAULT_TIME_OUT.toLong()
        var connectionTimeOut = DEFAULT_TIME_OUT.toLong()

        internal var isRelease = false

        fun builder(): BaseApi {
            return BaseApi(this)
        }
    }


    fun <T> createApi(t: Class<T>): T {
        return retrofit.create(t)
    }


}