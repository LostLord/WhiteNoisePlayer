package com.softwareproject.whitenoiseplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.softwareproject.whitenoiseplayer.repository.MusicRepository
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem

class RecommendViewModel(application: Application) : AndroidViewModel(application) {
    private val recommendList = Transformations.map(MusicRepository.getRecommendList()) {
        it
    }

    fun getRecommendList(): LiveData<List<MusicItem>>? {
        return recommendList
    }
}