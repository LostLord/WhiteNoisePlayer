package com.softwareproject.whitenoiseplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.databinding.ItemClickListener
import com.softwareproject.whitenoiseplayer.databinding.OftenListenItemBinding
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import java.lang.ClassCastException

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1
private const val TYPE_FOOTER = 2

class OftenListenAdapter(val itemClickListener: ItemClickListener) : ListAdapter<OftenListenDataItem, RecyclerView.ViewHolder>(OftenListenDiffCallback()) {

    fun addHeaderAndList(list: List<MusicItem>?) {
        val items = when (list) {
            null -> listOf(OftenListenDataItem.Header)
            else -> listOf(OftenListenDataItem.Header) + list.map { OftenListenDataItem.OftenListenItem(it) } + listOf(OftenListenDataItem.Footer)
        }
        submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder.from(parent)
            TYPE_ITEM -> ItemViewHolder.from(parent)
            TYPE_FOOTER -> FooterViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val musicItem = getItem(position) as OftenListenDataItem.OftenListenItem
                holder.bind(musicItem.musicItem, position, itemClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is OftenListenDataItem.Header -> TYPE_HEADER
            is OftenListenDataItem.OftenListenItem -> TYPE_ITEM
            is OftenListenDataItem.Footer -> TYPE_FOOTER
        }
    }

    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.main_recycler_view_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }

    class ItemViewHolder(val binding: OftenListenItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(musicItem: MusicItem, index: Int, clickListener: ItemClickListener) {
            binding.music = musicItem
            binding.number = "No.${index}"
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OftenListenItemBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): FooterViewHolder {
                val view = View(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150)
                return FooterViewHolder(view)
            }
        }
    }
}

class OftenListenDiffCallback : DiffUtil.ItemCallback<OftenListenDataItem>() {
    override fun areItemsTheSame(oldItem: OftenListenDataItem, newItem: OftenListenDataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OftenListenDataItem, newItem: OftenListenDataItem): Boolean {
        return oldItem == newItem
    }

}

sealed class OftenListenDataItem {
    data class OftenListenItem(val musicItem: MusicItem) : OftenListenDataItem() {
        override val id = musicItem.id
    }
    object Header: OftenListenDataItem() {
        override val id = Long.MIN_VALUE
    }
    object Footer: OftenListenDataItem() {
        override val id = Long.MAX_VALUE
    }
    abstract val id: Long
}