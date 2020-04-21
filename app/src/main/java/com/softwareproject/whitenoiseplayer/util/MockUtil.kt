package com.softwareproject.whitenoiseplayer.util

import com.softwareproject.whitenoiseplayer.data.MusicItem

fun getMockData(): List<MusicItem> {
    val testData = ArrayList<MusicItem>()
    testData.add(MusicItem(1, "", "", "", ""))
    testData.add(MusicItem(2, "", "", "", ""))
    testData.add(MusicItem(3, "", "", "", ""))
    testData.add(MusicItem(4, "", "", "", ""))

    return testData
}