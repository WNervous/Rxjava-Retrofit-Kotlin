package com.wys.wankotlinpractice.net.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val request: Request
        val httpUrl = originRequest.url().newBuilder()
            .addQueryParameter("paltform", "android")
            .addQueryParameter("version", "1.0.0").build()
        request = originRequest.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }


}