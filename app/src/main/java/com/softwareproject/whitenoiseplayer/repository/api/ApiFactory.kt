package com.softwareproject.whitenoiseplayer.repository.api

object ApiFactory {
    val baseApi = RetrofitFactory.retrofit("http://192.168.0.103").create(ApiService::class.java)
}