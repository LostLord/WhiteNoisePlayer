package com.softwareproject.whitenoiseplayer.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.softwareproject.whitenoiseplayer.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    val prefix = context.resources.getString(R.string.picture_dir)
    Glide.with(context).load(prefix + url).into(this)
}