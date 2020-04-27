package com.softwareproject.whitenoiseplayer.repository.data

data class MusicItem(
    var id: Long,
    var coverImage: String,
    var name: String,
    var audioPath: String,
    var brief: String
)