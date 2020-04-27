package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.DayRecommendAdapter

import com.softwareproject.whitenoiseplayer.databinding.FragmentRecommendBinding
import com.softwareproject.whitenoiseplayer.databinding.ItemClickListener
import com.softwareproject.whitenoiseplayer.util.getStatusBarHeight
import com.softwareproject.whitenoiseplayer.viewmodel.PlayingMusicViewModel
import com.softwareproject.whitenoiseplayer.viewmodel.RecommendViewModel

class RecommendFragment : Fragment() {
    private lateinit var binding: FragmentRecommendBinding
    private val recommendViewModel: RecommendViewModel by activityViewModels()
    private val playingMusicViewModel: PlayingMusicViewModel by activityViewModels()
    private val recommendAdapter = DayRecommendAdapter(ItemClickListener { musicItem ->
        playingMusicViewModel.setMusicItem(musicItem)
        NavHostFragment.findNavController(this).navigate(R.id.action_wrapFragment_to_playerFragment)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecommendBinding.inflate(inflater, container, false)

        this.binding = binding
        binding.recommendWrapper.apply {
            setPadding(paddingLeft, getStatusBarHeight(resources), paddingRight, paddingBottom)
        }

        binding.recommendRecyclerView.apply {
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = manager
            adapter = recommendAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recommendViewModel.getRecommendList()?.observe(requireActivity(), Observer {
            recommendAdapter.submitList(it)
        })
    }
}
