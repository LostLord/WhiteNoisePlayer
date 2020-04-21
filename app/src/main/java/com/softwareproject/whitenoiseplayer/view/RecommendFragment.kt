package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.softwareproject.whitenoiseplayer.adapter.RecommendMainAdapter
import com.softwareproject.whitenoiseplayer.databinding.FragmentRecommendBinding
import com.softwareproject.whitenoiseplayer.util.getStatusBarHeight

class RecommendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecommendBinding.inflate(inflater, container, false)

        binding.recommendWrapper.apply {
            setPadding(paddingLeft, getStatusBarHeight(resources), paddingRight, paddingBottom)
        }

        binding.recommendRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecommendMainAdapter()
        }

        return binding.root
    }
}
