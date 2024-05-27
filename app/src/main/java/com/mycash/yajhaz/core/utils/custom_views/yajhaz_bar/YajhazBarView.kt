package com.mycash.yajhaz.core.utils.custom_views.yajhaz_bar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mycash.yajhaz.databinding.LayoutYajhazBarViewBinding

class YajhazBarView(context: Context, attributes: AttributeSet? = null) :
    FrameLayout(context, attributes) {

    private val binding by lazy {
        LayoutYajhazBarViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    init {
        binding.root
    }

    fun setOnMenuClicked(onMenuClicked: () -> Unit) {
        binding.menuIv.setOnClickListener { onMenuClicked() }
    }

    fun setOnCartClicked(onCartClicked: () -> Unit) {
        binding.cartIv.setOnClickListener { onCartClicked() }
    }

    fun setOnBackCLicked(onBackCLicked: () -> Unit) {
        binding.backIv.setOnClickListener { onBackCLicked() }
    }
}