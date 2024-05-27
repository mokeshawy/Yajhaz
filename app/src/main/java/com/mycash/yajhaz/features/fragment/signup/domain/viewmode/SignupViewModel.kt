package com.mycash.yajhaz.features.fragment.signup.domain.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.yajhaz.core.error.ConfirmPasswordLessThanEightCharacter
import com.mycash.yajhaz.core.error.EmptyConfirmPassword
import com.mycash.yajhaz.core.error.EmptyEmail
import com.mycash.yajhaz.core.error.EmptyName
import com.mycash.yajhaz.core.error.EmptyPassword
import com.mycash.yajhaz.core.error.EmptyPhoneNumber
import com.mycash.yajhaz.core.error.InvalidEmail
import com.mycash.yajhaz.core.error.NameLessThanFourteenCharacter
import com.mycash.yajhaz.core.error.PasswordLessThanEightCharacter
import com.mycash.yajhaz.core.error.PasswordNotMatched
import com.mycash.yajhaz.core.error.PhoneNumberLessThanElevenNumber
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.isInvalidEmail
import com.mycash.yajhaz.features.fragment.signup.data.model.response.ClientRegisterResponseDto
import com.mycash.yajhaz.features.fragment.signup.domain.model.ClientRegisterUiModel
import com.mycash.yajhaz.features.fragment.signup.domain.repository.ClientRegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val clientRegisterRepository: ClientRegisterRepository) :
    ViewModel() {

    private val _clientRegisterResponseState =
        MutableStateFlow<State<ClientRegisterResponseDto>>(State.Initial())
    val clientRegisterResponseState = _clientRegisterResponseState.asStateFlow()

    private val _validationState = MutableStateFlow<State<Any>>(State.Initial())
    val validationState = _validationState.asStateFlow()


    fun validationInput(clientRegisterUiModel: ClientRegisterUiModel) = viewModelScope.launch {
        clientRegisterUiModel.validationInput()
    }

    private suspend fun ClientRegisterUiModel.validationInput() = when {

        name.isEmpty() -> _validationState.emit(getValidationError(EmptyName()))

        name.length < 14 ->
            _validationState.emit(getValidationError(NameLessThanFourteenCharacter()))

        email.isEmpty() -> _validationState.emit(getValidationError(EmptyEmail()))

        email.isInvalidEmail() -> _validationState.emit(getValidationError(InvalidEmail()))

        phone.isEmpty() -> _validationState.emit(getValidationError(EmptyPhoneNumber()))
        phone.length < 11 ->
            _validationState.emit(getValidationError(PhoneNumberLessThanElevenNumber()))

        password.isEmpty() ->
            _validationState.emit(getValidationError(EmptyPassword()))

        password.length < 8 ->
            _validationState.emit(getValidationError(PasswordLessThanEightCharacter()))

        confirmPassword.isEmpty() ->
            _validationState.emit(getValidationError(EmptyConfirmPassword()))

        confirmPassword.length < 8 ->
            _validationState.emit(getValidationError(ConfirmPasswordLessThanEightCharacter()))

        confirmPassword != password -> _validationState.emit(getValidationError(PasswordNotMatched()))

        else -> onValidInput(this)
    }

    private fun <E> getValidationError(error: Exception) =
        State.Error<E>(YajhazError.I(exception = error))


    private suspend fun onValidInput(clientRegisterUiModel: ClientRegisterUiModel) {
        _validationState.emit(State.Success(null))
        toSignup(clientRegisterUiModel)
    }

    private suspend fun toSignup(clientRegisterUiModel: ClientRegisterUiModel) {
        _clientRegisterResponseState.emit(State.Loading())
        clientRegisterRepository.signup(clientRegisterUiModel.toRegisterNewClient()).collect {
            _clientRegisterResponseState.emit(it)
        }
    }
}