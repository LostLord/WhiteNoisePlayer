package com.softwareproject.whitenoiseplayer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.DayRecommendItemBinding

class DayRecommendAdapter : ListAdapter<MusicItem, RecyclerView.ViewHolder>(DayRecommendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("recommend-list", "$viewType")
        return RecommendItem.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    class RecommendItem(val binding: DayRecommendItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecommendItem {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DayRecommendItemBinding.inflate(layoutInflater, parent, false)
                return RecommendItem(binding)
            }
        }
    }
}

class DayRecommendDiffCallback: DiffUtil.ItemCallback<MusicItem>() {
    override fun areItemsTheSame(oldItem: MusicItem, newItem: MusicItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MusicItem, newItem: MusicItem): Boolean {
        return oldItem == newItem
    }

}