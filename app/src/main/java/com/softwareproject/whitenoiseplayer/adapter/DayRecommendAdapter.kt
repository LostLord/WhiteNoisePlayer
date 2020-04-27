package com.softwareproject.whitenoiseplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.DayRecommendItemBinding
import com.softwareproject.whitenoiseplayer.databinding.ItemClickListener

class DayRecommendAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<MusicItem, RecyclerView.ViewHolder>(DayRecommendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecommendItem.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecommendItem).bind(getItem(position), itemClickListener)
    }

    class RecommendItem(val binding: DayRecommendItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(musicItem: MusicItem, clickListener: ItemClickListener) {
            binding.music = musicItem
            binding.clickListener = clickListener
        }

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