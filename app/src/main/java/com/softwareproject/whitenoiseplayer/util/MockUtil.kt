package com.softwareproject.whitenoiseplayer.util

import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
/*
MUSIC素材
 */
fun getMockData(): List<MusicItem> {
    val testData = ArrayList<MusicItem>()
    testData.add(MusicItem(1, "", "", "", ""))
    testData.add(MusicItem(2, "", "", "", ""))
    testData.add(MusicItem(3, "", "", "", ""))
    testData.add(MusicItem(4, "", "", "", ""))

    return testData
}