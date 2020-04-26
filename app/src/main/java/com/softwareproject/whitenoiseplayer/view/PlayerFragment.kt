package com.softwareproject.whitenoiseplayer.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.softwareproject.whitenoiseplayer.R

import com.softwareproject.whitenoiseplayer.databinding.FragmentPlayerBinding
import com.softwareproject.whitenoiseplayer.viewmodel.PlayingMusicViewModel

class PlayerFragment : Fragment() {
    private val playingMusicViewModel: PlayingMusicViewModel by activityViewModels()
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        this.binding = binding
        binding.playingMusic = playingMusicViewModel
        binding.backButton.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
        binding.progressBar.setProgressFormatter(null)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val musicDir = activity?.resources?.getString(R.string.music_dir)
        val pictureDir = activity?.resources?.getString(R.string.picture_dir)
        Glide.with(this).load("${pictureDir}1.jpg").into(binding.musicBackground)
        playingMusicViewModel.setDataSource("${musicDir}1.mp3")

        playingMusicViewModel.getPlayingStatus().observe(requireActivity(), Observer { isPlaying ->
            val drawable = activity?.getDrawable(if (isPlaying) R.drawable.ic_stop else R.drawable.play_icon)
            binding.pauseButton.setImageDrawable(drawable)
        })
        playingMusicViewModel.getDuration().observe(requireActivity(), Observer { duration ->
            val totalDurationSeconds = duration / 1000
            val minutes = totalDurationSeconds / 60
            val seconds = totalDurationSeconds % 60
            binding.duration.text = "/${minutes}:${String.format("%02d", seconds)}"
            binding.progressBar.max = totalDurationSeconds
        })
        playingMusicViewModel.getCurrentTime().observe(requireActivity(), Observer { currentTime ->
            val minutes = currentTime / 60
            val seconds = currentTime % 60
            binding.currentTime.text = "${String.format("%02d", minutes)}:${String.format("%02d", seconds)}"
            binding.progressBar.progress = currentTime
        })
    }

}
