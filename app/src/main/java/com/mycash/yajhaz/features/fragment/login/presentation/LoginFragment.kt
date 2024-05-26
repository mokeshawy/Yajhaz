package com.mycash.yajhaz.features.fragment.login.presentation

import android.os.Bundle
import android.view.View
import com.mycash.yajhaz.core.base_fragment.BaseFragment
import com.mycash.yajhaz.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}