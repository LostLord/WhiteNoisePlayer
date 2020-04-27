package com.softwareproject.whitenoiseplayer.repository

import androidx.lifecycle.MutableLiveData
import com.softwareproject.whitenoiseplayer.repository.api.ApiFactory
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MusicRepository {
    private val api = ApiFactory.baseApi

    fun getOftenList(): MutableLiveData<List<MusicItem>> {
        val listData = MutableLiveData<List<MusicItem>>()
        api.getOftenList().enqueue(object : Callback<List<MusicItem>> {
            override fun onFailure(call: Call<List<MusicItem>>, t: Throwable) {
                listData.postValue(null)
            }

            override fun onResponse(
                call: Call<List<MusicItem>>,
                response: Response<List<MusicItem>>
            ) {
                if (response.isSuccessful) {
                    listData.postValue(response.body())
                }
            }

        })
        return listData
    }
}