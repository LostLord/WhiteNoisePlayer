package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.OftenListenAdapter
import com.softwareproject.whitenoiseplayer.adapter.OftenListenItemDecoration
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.FragmentMainBinding
import com.softwareproject.whitenoiseplayer.databinding.ItemClickListener
import com.softwareproject.whitenoiseplayer.util.getStatusBarHeight
import com.softwareproject.whitenoiseplayer.viewmodel.MainViewModel
import com.softwareproject.whitenoiseplayer.viewmodel.PlayingMusicViewModel
/*
for main page
 */
class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val playingMusicViewModel: PlayingMusicViewModel by activityViewModels()
    private val oftenListAdapter = OftenListenAdapter(ItemClickListener { musicItem ->
        playingMusicViewModel.setMusicItem(musicItem)
        NavHostFragment.findNavController(this).navigate(R.id.action_wrapFragment_to_playerFragment)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)

        binding.mainLayout.apply {
            setPadding(paddingLeft, getStatusBarHeight(resources), paddingRight, paddingBottom)
        }
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = oftenListAdapter
            addItemDecoration(OftenListenItemDecoration())
        }
        binding.playingMusic = playingMusicViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getOftenList()?.observe(requireActivity(), Observer {
            oftenListAdapter.addHeaderAndList(it)
        })
    }
}
