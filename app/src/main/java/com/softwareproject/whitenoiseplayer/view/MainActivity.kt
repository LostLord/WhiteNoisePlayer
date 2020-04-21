package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.adapter.MainViewPagerAdapter
import com.softwareproject.whitenoiseplayer.base.BaseActivity
import com.softwareproject.whitenoiseplayer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding = binding
    }

}
