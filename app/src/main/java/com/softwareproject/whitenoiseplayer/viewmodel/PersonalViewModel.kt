package com.softwareproject.whitenoiseplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.softwareproject.whitenoiseplayer.repository.MusicRepository
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem

class PersonalViewModel(application: Application): AndroidViewModel(application) {
    private val personalListenList = Transformations.map(MusicRepository.getPersonalListen()) {
        it
    }

    fun getPersonalListenList(): LiveData<List<MusicItem>>? {
        return personalListenList
    }
}