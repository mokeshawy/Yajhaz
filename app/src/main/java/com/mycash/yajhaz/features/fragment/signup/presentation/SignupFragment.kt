package com.mycash.yajhaz.features.fragment.signup.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.base_fragment.BaseFragment
import com.mycash.yajhaz.core.error.ConfirmPasswordLessThanEightCharacter
import com.mycash.yajhaz.core.error.EmptyConfirmPassword
import com.mycash.yajhaz.core.error.EmptyEmail
import com.mycash.yajhaz.core.error.EmptyName
import com.mycash.yajhaz.core.error.EmptyPassword
import com.mycash.yajhaz.core.error.EmptyPhoneNumber
import com.mycash.yajhaz.core.error.InvalidEmail
import com.mycash.yajhaz.core.error.NameLessThanFourteenCharacter
import com.mycash.yajhaz.core.error.ResponseMessageException
import com.mycash.yajhaz.core.error.PasswordLessThanEightCharacter
import com.mycash.yajhaz.core.error.PasswordNotMatched
import com.mycash.yajhaz.core.error.PhoneNumberLessThanElevenNumber
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.dialogs.snack_bar.YajhazSnackBarBuilder
import com.mycash.yajhaz.core.utils.dialogs.yajhaz_dialog.YajhazDialogBuilder
import com.mycash.yajhaz.databinding.FragmentSignupBinding
import com.mycash.yajhaz.features.fragment.signup.domain.model.ClientRegisterUiModel
import com.mycash.yajhaz.features.fragment.signup.domain.viewmode.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val binding by lazy { FragmentSignupBinding.inflate(layoutInflater) }
    private val viewModel: SignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupViews()
    }


    private fun FragmentSignupBinding.setupViews() {
        setOnLoginClicked()
        setOnSignupClicked()
        lifecycleScope.apply {
            launch { observeOnValidationInput() }
            launch { observeOnClientRegisterResponseState() }
        }
    }

    private fun FragmentSignupBinding.setOnLoginClicked() =
        loginTv.setOnClickListener { popBackstack() }


    private fun FragmentSignupBinding.setOnSignupClicked() = signupBtn.setOnClickListener {
        if (connectivityManager.isNetworkConnected.value == false) {
            showSnackBarError(R.string.internetConnection)
            return@setOnClickListener
        }
        handleSignupRequest()
    }

    private fun FragmentSignupBinding.handleSignupRequest() {
        val clientRegisterUiModel = getClientRegisterUiModel()
        viewModel.validationInput(clientRegisterUiModel)
    }

    private fun FragmentSignupBinding.getClientRegisterUiModel() = ClientRegisterUiModel(
        name = nameEt.text.toString(),
        email = emailTilEt.text.toString(),
        password = passwordEt.text.toString(),
        confirmPassword = confirmPasswordEt.text.toString(),
        phone = phoneNumberEt.text.toString()

    )

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
                is EmptyName -> showSnackBarError(R.string.enterYourName)
                is NameLessThanFourteenCharacter ->
                    showSnackBarError(R.string.nameLessThanFourteenCharacter)

                is EmptyEmail -> showSnackBarError(R.string.pleaseEnterYourEmail)
                is InvalidEmail -> showSnackBarError(R.string.pleaseEnterAValidEmail)
                is EmptyPhoneNumber -> showSnackBarError(R.string.pleaseEnterYourPhone)
                is PhoneNumberLessThanElevenNumber -> showSnackBarError(R.string.PhoneNumberLessThanElevenNumber)
                is EmptyPassword -> showSnackBarError(R.string.pleaseEnterYourPassword)
                is PasswordLessThanEightCharacter -> showSnackBarError(R.string.passwordLessThanEightCharacter)
                is EmptyConfirmPassword -> showSnackBarError(R.string.pleaseEnterConfirmPassword)
                is ConfirmPasswordLessThanEightCharacter -> showSnackBarError(R.string.confirmPasswordLessThanEightCharacter)
                is PasswordNotMatched -> showSnackBarError(R.string.passwordNotMatched)
            }
        }
    }

    private fun showSnackBarError(@StringRes message: Int) {
        YajhazSnackBarBuilder(requireActivity())
            .setEndIcon(R.drawable.ic_vector_close)
            .setMessage(message).build(requireView()).show()
    }


    private suspend fun observeOnClientRegisterResponseState() {
        viewModel.clientRegisterResponseState.collect {
            hideProgressDialog()
            when (it) {
                is State.Error -> it.error.handleClientRegisterResponseError()
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> showSignupResponseSuccessPopup()
            }
        }
    }


    private fun YajhazError.handleClientRegisterResponseError() {
        handleError {
            val message = logMessage ?: ""
            when (exception) {
                is ResponseMessageException -> showSignupResponsePopupError(message)
            }
        }
    }

    private fun showSignupResponsePopupError(message: String) {
        YajhazDialogBuilder(requireActivity())
            .setIcon(R.drawable.ic_vector_check_circle)
            .setTitle(R.string.warning)
            .setMessage(message)
            .setTopButton(R.string.ok) { }
            .show()
    }

    private fun showSignupResponseSuccessPopup() {
        YajhazDialogBuilder(requireActivity())
            .setIcon(R.drawable.ic_vector_error)
            .setTitle(R.string.done)
            .setMessage(R.string.theRegisterAccountHasBeenSuccess)
            .setTopButton(R.string.ok) { popBackstack() }
            .setCancelable(false)
            .show()
    }

    private fun popBackstack() = findNavController().popBackStack()
}