package com.softwareproject.whitenoiseplayer.databinding

import com.softwareproject.whitenoiseplayer.repository.data.MusicItem

class ItemClickListener(val clickListener: (musicItem: MusicItem) -> Unit) {
    fun onClick(musicItem: MusicItem) = clickListener(musicItem)
}