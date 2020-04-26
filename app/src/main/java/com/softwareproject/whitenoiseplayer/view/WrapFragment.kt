package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.MainViewPagerAdapter
import com.softwareproject.whitenoiseplayer.databinding.FragmentWrapBinding

class WrapFragment : Fragment() {
    private lateinit var binding: FragmentWrapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWrapBinding.inflate(inflater, container, false)
        this.binding = binding
        // navigation set
        binding.navigationView.itemIconTintList = null
        binding.navigationView.setOnNavigationItemSelectedListener(navigationOnItemSelectedListener)

        initViewPager()
        return binding.root
    }

    private fun initViewPager() {
        val mFragments = ArrayList<Fragment>()
        mFragments.add(RecommendFragment())
        mFragments.add(MainFragment())
        mFragments.add(PersonalFragment())
        binding.mainViewPager.adapter = MainViewPagerAdapter(requireActivity(), mFragments)
        binding.mainViewPager.currentItem = 2
    }

    private val navigationOnItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationRecommend -> {
                    binding.mainViewPager.currentItem = 0
                }
                R.id.navigationMain -> {
                    binding.mainViewPager.currentItem = 1
                }
                R.id.navigationPersonal -> {
                    binding.mainViewPager.currentItem = 2
                }
            }
            true
        }
}
