package com.softwareproject.whitenoiseplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pdog.dimension.dp
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.ListenRecorderItemBinding
import com.softwareproject.whitenoiseplayer.databinding.PersonalHeaderBinding
import kotlinx.android.synthetic.main.personal_header.view.*
import java.lang.ClassCastException

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1
private const val TYPE_FOOTER = 2

class ListenRecorderAdapter() : ListAdapter<RecorderDataItem, RecyclerView.ViewHolder>(RecorderDiffCallback()) {
    fun addHeaderAndList(list: List<MusicItem>?) {
        val items = when (list) {
            null -> listOf(RecorderDataItem.Header)
            else -> listOf(RecorderDataItem.Header) + list.map { RecorderDataItem.RecorderItem(it.id) } + listOf(RecorderDataItem.Footer)
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
            is HeaderViewHolder -> {
                holder.bind()
            }
            is ItemViewHolder -> {
                holder.setPaddingHorizontal()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RecorderDataItem.Header -> TYPE_HEADER
            is RecorderDataItem.RecorderItem -> TYPE_ITEM
            is RecorderDataItem.Footer -> TYPE_FOOTER
        }
    }

    class HeaderViewHolder(val binding: PersonalHeaderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.userAvatar.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_wrapFragment_to_playerFragment)
            }
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PersonalHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class ItemViewHolder private constructor(val binding: ListenRecorderItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setPaddingHorizontal() {
            binding.wrapper.apply {
                setPadding(20.dp, paddingTop, 20.dp, paddingBottom)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListenRecorderItemBinding.inflate(layoutInflater, parent, false)
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

class RecorderDiffCallback : DiffUtil.ItemCallback<RecorderDataItem>() {
    override fun areItemsTheSame(oldItem: RecorderDataItem, newItem: RecorderDataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecorderDataItem, newItem: RecorderDataItem): Boolean {
        return oldItem == newItem
    }

}

sealed class RecorderDataItem {
    data class RecorderItem(val itemId: Long) : RecorderDataItem() {
        override val id = itemId
    }
    object Header: RecorderDataItem() {
        override val id = Long.MIN_VALUE
    }
    object Footer: RecorderDataItem() {
        override val id = Long.MAX_VALUE
    }
    abstract val id: Long
}