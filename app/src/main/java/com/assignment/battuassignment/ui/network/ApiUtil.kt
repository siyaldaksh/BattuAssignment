package com.assignment.battuassignment.ui.network

class ApiUtil() {

    companion object{
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun getUi(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }


}