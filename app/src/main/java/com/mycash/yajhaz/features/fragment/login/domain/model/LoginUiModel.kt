package com.mycash.yajhaz.features.fragment.login.domain.model

import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto

data class LoginUiModel(
    val email: String,
    val password: String
) {
    fun toLogin() = LoginRequestDto(email, password)
}
