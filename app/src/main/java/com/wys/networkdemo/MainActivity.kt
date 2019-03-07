package com.wys.networkdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wys.networklib.bean.CommonResponse
import com.wys.networklib.observer.CommonObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getBanner()
    }

    private fun getBanner() {
        ApiService.sApi.wanApi().getHomeBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CommonObserver<List<Objects>>() {
                override fun onSuccess(t: CommonResponse<List<Objects>>) {
                    Log.d("Mainactivity", t.data.toString())
                }

                override fun onFailure(e: Throwable) {

                }

            })
    }
}
