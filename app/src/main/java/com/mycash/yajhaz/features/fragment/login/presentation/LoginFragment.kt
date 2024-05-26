package com.mycash.yajhaz.features.fragment.login.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.base_fragment.BaseFragment
import com.mycash.yajhaz.core.error.EmptyEmail
import com.mycash.yajhaz.core.error.EmptyPassword
import com.mycash.yajhaz.core.error.InvalidEmail
import com.mycash.yajhaz.core.error.OperationMessage
import com.mycash.yajhaz.core.error.PasswordLetThanEightCharacter
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.dialogs.snack_bar.YajhazSnackBarBuilder
import com.mycash.yajhaz.core.utils.dialogs.yajhaz_dialog.YajhazDialogBuilder
import com.mycash.yajhaz.databinding.FragmentLoginBinding
import com.mycash.yajhaz.features.fragment.login.domain.model.LoginUiModel
import com.mycash.yajhaz.features.fragment.login.domain.viewmode.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupViews()
    }


    private fun FragmentLoginBinding.setupViews() {
        setOnLoginBtnClicked()
        setOnSignupClicked()
        lifecycleScope.apply {
            launch { observeOnValidationInput() }
            launch { observeOnLoginResponseState() }
        }
    }


    private fun FragmentLoginBinding.setOnSignupClicked() = singUpTv.setOnClickListener {
        //TODO NAVIGATE TO SIGNUP SCREEN
    }


    private fun FragmentLoginBinding.setOnLoginBtnClicked() = loginBtn.setOnClickListener {
        if (connectivityManager.isNetworkConnected.value == false) {
            showSnackBarError(R.string.internetConnection)
            return@setOnClickListener
        }
        handleUserLoginRequest()
    }

    private fun FragmentLoginBinding.handleUserLoginRequest() {
        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()
        val requestDto = LoginUiModel(email = email, password = password)
        viewModel.validationInput(requestDto)
    }

    private suspend fun observeOnValidationInput() {
        viewModel.validationState.collect {
            hideProgressDialog()
            when (it) {
                is State.Error -> it.error.handleValidationError()
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> {}
            }
        }
    }

    private fun YajhazError.handleValidationError() {
        handleError {
            when (exception) {
                is EmptyEmail -> showSnackBarError(R.string.pleaseEnterYourEmail)
                is InvalidEmail -> showSnackBarError(R.string.pleaseEnterAValidEmail)
                is EmptyPassword -> showSnackBarError(R.string.pleaseEnterYourPassword)
                is PasswordLetThanEightCharacter -> showSnackBarError(R.string.write8CharacterAtLeast)
            }
        }
    }

    private fun showSnackBarError(@StringRes message: Int) {
        YajhazSnackBarBuilder(requireActivity())
            .setEndIcon(R.drawable.ic_vector_close)
            .setMessage(message).build(requireView()).show()
    }


    private suspend fun observeOnLoginResponseState() {
        viewModel.loginResponseState.collect {
            hideProgressDialog()
            when (it) {
                is State.Error -> it.error.handleLoginResponseError()
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> {
                    //TODO NAVIGATE TO HOME SCREEN
                }
            }
        }
    }


    private fun YajhazError.handleLoginResponseError() {
        handleError {
            val message = logMessage ?: ""
            when (exception) {
                is OperationMessage -> showLoginResponsePopupError(message)
            }
        }
    }

    private fun showLoginResponsePopupError(message: String) {
        YajhazDialogBuilder(requireActivity())
            .setIcon(R.drawable.ic_vector_error)
            .setTitle(R.string.warning)
            .setMessage(message)
            .setTopButton(R.string.ok) {}
            .show()
    }
}