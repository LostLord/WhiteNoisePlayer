package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.OftenListenAdapter
import com.softwareproject.whitenoiseplayer.adapter.OftenListenItemDecoration
import com.softwareproject.whitenoiseplayer.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)

        val listAdapter = OftenListenAdapter()
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(OftenListenItemDecoration())
        }
        listAdapter.addHeaderAndList(getMockData())
        return binding.root
    }

    private fun getMockData(): List<MusicItem> {
        val testData = ArrayList<MusicItem>()
        testData.add(MusicItem(1, "", "", "", ""))
        testData.add(MusicItem(2, "", "", "", ""))
        testData.add(MusicItem(3, "", "", "", ""))
        testData.add(MusicItem(4, "", "", "", ""))

        return testData
    }
}
