package com.softwareproject.whitenoiseplayer.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.softwareproject.whitenoiseplayer.R
import com.softwareproject.whitenoiseplayer.base.BaseActivity
import com.softwareproject.whitenoiseplayer.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding = binding
    }

}
