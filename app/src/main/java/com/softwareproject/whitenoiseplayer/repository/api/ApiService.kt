package com.softwareproject.whitenoiseplayer.repository.api

import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("json/total.json")
    fun getTotalMusic(): Call<List<MusicItem>>

    @GET("json/often.json")
    fun getOftenList(): Call<List<MusicItem>>

    @GET("json/personal_listen.json")
    fun getPersonalListen(): Call<List<MusicItem>>

    @GET("json/recommend.json")
    fun getRecommendList(): Call<List<MusicItem>>
}