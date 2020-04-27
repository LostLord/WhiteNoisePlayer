package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
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
import com.softwareproject.whitenoiseplayer.adapter.ListenRecorderAdapter
import com.softwareproject.whitenoiseplayer.repository.data.MusicItem
import com.softwareproject.whitenoiseplayer.databinding.FragmentPersonalBinding
import com.softwareproject.whitenoiseplayer.databinding.ItemClickListener
import com.softwareproject.whitenoiseplayer.viewmodel.PersonalViewModel
import com.softwareproject.whitenoiseplayer.viewmodel.PlayingMusicViewModel

class PersonalFragment : Fragment() {
    private val personalViewModel: PersonalViewModel by activityViewModels()
    private val playingMusicViewModel: PlayingMusicViewModel by activityViewModels()
    private lateinit var binding: FragmentPersonalBinding
    private val recorderAdapter = ListenRecorderAdapter(ItemClickListener { musicItem ->
        playingMusicViewModel.setMusicItem(musicItem)
        NavHostFragment.findNavController(this).navigate(R.id.action_wrapFragment_to_playerFragment)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPersonalBinding>(inflater, R.layout.fragment_personal, container, false)

        binding.personalRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recorderAdapter
        }

        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalViewModel.getPersonalListenList()?.observe(requireActivity(), Observer {
            recorderAdapter.addHeaderAndList(it)
        })
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
