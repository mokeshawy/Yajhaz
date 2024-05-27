package com.mycash.yajhaz.features.fragment.signup.domain.model

import com.mycash.yajhaz.features.fragment.signup.data.model.request.ClientRegisterRequestDto

data class ClientRegisterUiModel(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phone: String
) {
    fun toRegisterNewClient() = ClientRegisterRequestDto(
        name = name,
        email = email,
        password = password,
        phone = phone
    )
}
