package com.wys.networklib.bean

data class CommonResponse<T>(var errorCode: Int, var errorMsg: String, var data: T) {
    fun isSuccess(): Boolean = errorCode == 0
}