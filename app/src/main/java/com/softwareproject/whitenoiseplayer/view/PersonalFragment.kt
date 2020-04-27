package com.softwareproject.whitenoiseplayer.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.ListenRecorderAdapter
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.FragmentPersonalBinding
import com.softwareproject.whitenoiseplayer.util.getScreenHeight

class PersonalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPersonalBinding>(inflater, R.layout.fragment_personal, container, false)

        val screenHeight = getScreenHeight(context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
        val recorderAdapter = ListenRecorderAdapter()
        binding.personalRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recorderAdapter
        }
        recorderAdapter.addHeaderAndList(getMockData())

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
