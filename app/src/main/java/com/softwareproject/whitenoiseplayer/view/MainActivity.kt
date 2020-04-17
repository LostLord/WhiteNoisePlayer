package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigation set
        navigationView.itemIconTintList = null
        navigationView.setOnNavigationItemSelectedListener(navigationOnItemSelectedListener)
    }

    private val navigationOnItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            return@OnNavigationItemSelectedListener true
        }
}
