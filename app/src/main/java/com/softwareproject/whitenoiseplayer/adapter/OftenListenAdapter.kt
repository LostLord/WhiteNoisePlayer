package com.softwareproject.whitenoiseplayer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.data.MusicItem
import java.lang.ClassCastException

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1
private const val TYPE_FOOTER = 2

class OftenListenAdapter() : ListAdapter<DataItem, RecyclerView.ViewHolder>(OftenListenDiffCallback()) {

    fun addHeaderAndList(list: List<MusicItem>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.OftenListenItem(it.id) } + listOf(DataItem.Footer)
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
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> TYPE_HEADER
            is DataItem.OftenListenItem -> TYPE_ITEM
            is DataItem.Footer -> TYPE_FOOTER
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
    object Footer: DataItem() {
        override val id = Long.MAX_VALUE
    }
    abstract val id: Long
}