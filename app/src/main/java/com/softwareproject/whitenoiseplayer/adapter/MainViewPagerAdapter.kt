package com.softwareproject.whitenoiseplayer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(private var fragmentActivity: FragmentActivity, private var mFragments: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}