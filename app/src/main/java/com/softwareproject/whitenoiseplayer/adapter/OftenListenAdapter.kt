package com.softwareproject.whitenoiseplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.R

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

class OftenListenAdapter(val musicList: List<String>) : ListAdapter<DataItem, RecyclerView.ViewHolder>(OftenListenDiffCallback()) {

    init {
        val items = listOf(DataItem.Header) + musicList.map { DataItem.OftenListenItem(it.toLong()) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> TYPE_HEADER
            is DataItem.OftenListenItem -> TYPE_ITEM
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

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.often_listen_item, parent, false)
                return ItemViewHolder(view)
            }
        }
    }
}

class OftenListenDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}

sealed class DataItem {
    data class OftenListenItem(val itemId: Long) : DataItem() {
        override val id = itemId
    }
    object Header: DataItem() {
        override val id = Long.MIN_VALUE
    }
    abstract val id: Long
}