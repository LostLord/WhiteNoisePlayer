package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.MainViewPagerAdapter
import com.softwareproject.whitenoiseplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigation set
        navigationView.itemIconTintList = null
        navigationView.setOnNavigationItemSelectedListener(navigationOnItemSelectedListener)

        initViewPager()
    }

    private fun initViewPager() {
        val mFragments = ArrayList<Fragment>()
        mFragments.add(RecommendFragment())
        mFragments.add(MainFragment())
        mFragments.add(PersonalFragment())
        mainViewPager.adapter = MainViewPagerAdapter(this, mFragments)
        mainViewPager.currentItem = 2
    }

    private val navigationOnItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationRecommend -> {
                    mainViewPager.currentItem = 0
                }
                R.id.navigationMain -> {
                    mainViewPager.currentItem = 1
                }
                R.id.navigationPersonal -> {
                    mainViewPager.currentItem = 2
                }
            }
            true
        }
}
