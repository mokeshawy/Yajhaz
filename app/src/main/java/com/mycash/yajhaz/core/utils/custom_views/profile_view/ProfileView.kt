package com.mycash.yajhaz.core.utils.custom_views.profile_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.mycash.yajhaz.databinding.LayoutProfileViewBinding

class ProfileView(context: Context, attributes: AttributeSet? = null) :
    FrameLayout(context, attributes) {

    private val binding by lazy {
        LayoutProfileViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.root
    }

    fun setUserNameTv(setUserNameTv: (TextView) -> Unit) {
        setUserNameTv(binding.userNameTv)
    }

    fun setAddressTv(setAddressTv: (TextView) -> Unit) {
        setAddressTv(binding.addressTv)
    }
}