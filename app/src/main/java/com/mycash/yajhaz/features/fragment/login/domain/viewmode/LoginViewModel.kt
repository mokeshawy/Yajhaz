package com.mycash.yajhaz.features.fragment.login.domain.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.yajhaz.core.error.EmptyEmail
import com.mycash.yajhaz.core.error.EmptyPassword
import com.mycash.yajhaz.core.error.InvalidEmail
import com.mycash.yajhaz.core.error.PasswordLetThanEightCharacter
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.isInvalidEmail
import com.mycash.yajhaz.features.fragment.login.data.model.response.LoginResponseDto
import com.mycash.yajhaz.features.fragment.login.domain.model.LoginUiModel
import com.mycash.yajhaz.features.fragment.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginResponseState = MutableStateFlow<State<LoginResponseDto>>(State.Initial())
    val loginResponseState = _loginResponseState.asStateFlow()

    private val _validationState = MutableStateFlow<State<Any>>(State.Initial())
    val validationState = _validationState.asStateFlow()


    fun validationInput(loginUiModel: LoginUiModel) = viewModelScope.launch {
        loginUiModel.validationInput()
    }

    private suspend fun LoginUiModel.validationInput() = when {
        email.isEmpty() -> _validationState.emit(getValidationError(EmptyEmail()))
        email.isInvalidEmail() -> _validationState.emit(getValidationError(InvalidEmail()))
        password.isEmpty() -> _validationState.emit(getValidationError(EmptyPassword()))
        password.length < 8 -> _validationState.emit(getValidationError(PasswordLetThanEightCharacter()))
        else -> onValidInput(this)
    }

    private fun <E> getValidationError(error: Exception) =
        State.Error<E>(YajhazError.I(exception = error))


    private suspend fun onValidInput(loginUiModel: LoginUiModel) {
        _validationState.emit(State.Success(null))
        toLogin(loginUiModel)
    }

    private suspend fun toLogin(loginUiModel: LoginUiModel) {
        _loginResponseState.emit(State.Loading())
        loginUseCase(loginUiModel.toLogin()).collect {
            _loginResponseState.emit(it)
        }
    }
}