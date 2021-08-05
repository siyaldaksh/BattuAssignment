package com.assignment.battuassignment.ui.service


import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.assignment.battuassignment.ui.network.ApiUtil
import com.assignment.battuassignment.ui.network.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyService : Service() {

    private val binder = MyBinder()

    var arrayList : List<UserDetail>? = null

    private var mCallBack: CallBack? = null

    fun setCallBack(callBack: CallBack?) {
        mCallBack = callBack
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        gettingDataFromServer()

        return super.onStartCommand(intent, flags, startId)
    }


    fun gettingDataFromServer(){

        val apiService = ApiUtil.getUi()

        val call = apiService.getUiData()

        call.enqueue(object : Callback<List<UserDetail>> {
            override fun onResponse(
                call: Call<List<UserDetail>>,
                response: Response<List<UserDetail>>
            )
            {
                stopSelf()
                arrayList = response.body()
                arrayList?.let { mCallBack?.onsuccess(it) }
            }

            override fun onFailure(call: Call<List<UserDetail>>, t: Throwable) {
                stopSelf()
                mCallBack?.onfailed()
            }

        })
    }

    interface CallBack {
        fun onsuccess(arrayList:List<UserDetail>)
        fun onfailed()
    }

}