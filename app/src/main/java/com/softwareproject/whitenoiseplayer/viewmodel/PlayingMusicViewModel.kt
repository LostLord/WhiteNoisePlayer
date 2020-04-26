package com.softwareproject.whitenoiseplayer.viewmodel

import android.app.Application
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class PlayingMusicViewModel(application: Application) : AndroidViewModel(application) {
    private val player = MediaPlayer()
    private var currentTime = MutableLiveData<Int>()
    private var isPlaying = MutableLiveData<Boolean>()
    private val duration = MutableLiveData<Int>()
    private var timer = Timer()

    init {
        player.setOnPreparedListener {
            player.start()
            isPlaying.value = true
            duration.value = player.duration
            cancelTimer()
            setTimer()
        }
        player.setOnCompletionListener {
            cancelTimer()
        }
    }

    fun getPlayingStatus(): LiveData<Boolean> {
        return isPlaying
    }

    fun getDuration(): LiveData<Int> {
        return duration
    }

    fun getCurrentTime(): LiveData<Int> {
        return currentTime
    }

    fun setDataSource(uri: String) {
        player.apply {
            reset()
            setDataSource(uri)
            prepareAsync()
        }
    }

    fun play() {
        if (player.isPlaying) {
            player.pause()
            isPlaying.value = false
            cancelTimer()
        } else {
            player.start()
            isPlaying.value = true
            setTimer()
        }
    }

    private fun setTimer() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (player.isPlaying) {
                    currentTime.postValue(player.currentPosition / 1000)
                }
            }
        }, 0, 500)
    }

    private fun cancelTimer() {
        timer.cancel()
        timer.purge()
    }
}