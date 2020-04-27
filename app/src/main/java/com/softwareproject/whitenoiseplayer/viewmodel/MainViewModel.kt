package com.softwareproject.whitenoiseplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.softwareproject.whitenoiseplayer.repository.MusicRepository
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val oftenList = Transformations.map(MusicRepository.getOftenList()) {
        it
    }

    fun getOftenList(): LiveData<List<MusicItem>>? {
        return oftenList
    }
}