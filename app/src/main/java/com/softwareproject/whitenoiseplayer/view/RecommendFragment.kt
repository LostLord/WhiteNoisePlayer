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
import com.softwareproject.whitenoiseplayer.util.DayTime
import com.softwareproject.whitenoiseplayer.util.getDayTime
import com.softwareproject.whitenoiseplayer.util.getStatusBarHeight
import com.softwareproject.whitenoiseplayer.viewmodel.PlayingMusicViewModel
import com.softwareproject.whitenoiseplayer.viewmodel.RecommendViewModel
/*
for recommend page
 */
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

        binding.playingMusic = playingMusicViewModel
        setTimeTextAndImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recommendViewModel.getRecommendList()?.observe(requireActivity(), Observer {
            recommendAdapter.submitList(it)
        })
    }

    private fun setTimeTextAndImage() {
        when (getDayTime()) {
            DayTime.MORNING -> {
                binding.dayTimeText.text = "早上好"
                binding.dayImage.setImageDrawable(activity?.getDrawable(R.drawable.morning))
            }
            DayTime.NOON -> {
                binding.dayTimeText.text = "中午好"
                binding.dayImage.setImageDrawable(activity?.getDrawable(R.drawable.noon))
            }
            DayTime.AFTERNOON -> {
                binding.dayTimeText.text = "下午好"
                binding.dayImage.setImageDrawable(activity?.getDrawable(R.drawable.afternoon))
            }
            DayTime.NIGHT -> {
                binding.dayTimeText.text = "晚上好"
                binding.dayImage.setImageDrawable(activity?.getDrawable(R.drawable.night))
            }
        }
    }
}
