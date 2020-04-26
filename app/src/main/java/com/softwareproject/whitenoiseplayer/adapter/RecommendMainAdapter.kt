package com.softwareproject.whitenoiseplayer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.softwareproject.whitenoiseplayer.databinding.DayRecommendRecyclerViewBinding
import com.softwareproject.whitenoiseplayer.databinding.DayShowBinding
import com.softwareproject.whitenoiseplayer.util.getMockData
import java.lang.ClassCastException

private const val TYPE_HEADER = 0
private const val TYPE_LIST = 1

class RecommendMainAdapter : ListAdapter<RecommendDataItem, RecyclerView.ViewHolder>(RecommendMainDiffCallback()) {
    init {
        val items = listOf(RecommendDataItem.Header)
        submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("recommend", "$viewType : create")
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder.from(parent)
            TYPE_LIST -> HorizontalRecyclerView.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("recommend", "$position : $itemCount")
        when (holder) {
            is HorizontalRecyclerView -> {
                Log.d("recommend", "recycler")
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            RecommendDataItem.Header -> TYPE_HEADER
            RecommendDataItem.RecommendList -> TYPE_LIST
        }
    }

    class HeaderViewHolder(binding: DayShowBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DayShowBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class HorizontalRecyclerView(val binding: DayRecommendRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }

        companion object {
            fun from(parent: ViewGroup): HorizontalRecyclerView {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DayRecommendRecyclerViewBinding.inflate(layoutInflater, parent, true)
                val recommendAdapter = DayRecommendAdapter()
                recommendAdapter.submitList(getMockData())
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(binding.recyclerView.context)
                    adapter = recommendAdapter
                    Log.d("recommend", "${recommendAdapter.itemCount} - aaa")
                }
                return HorizontalRecyclerView(binding)
            }
        }
    }
}

class RecommendMainDiffCallback : DiffUtil.ItemCallback<RecommendDataItem>() {
    override fun areItemsTheSame(oldItem: RecommendDataItem, newItem: RecommendDataItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecommendDataItem, newItem: RecommendDataItem): Boolean {
        return oldItem == newItem
    }

}

sealed class RecommendDataItem {
    object Header: RecommendDataItem()
    object RecommendList: RecommendDataItem()
}