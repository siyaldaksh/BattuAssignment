package com.assignment.battuassignment.ui.network



import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface Api {

    @GET("users")
    fun getUiData(): Call<List<UserDetail>>

}