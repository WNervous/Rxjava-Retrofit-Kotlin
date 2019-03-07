package com.wys.networkdemo

import com.wys.networklib.bean.CommonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface Api {
    @GET("banner/json")
    fun getHomeBanner(): Observable<CommonResponse<List<Objects>>>


}