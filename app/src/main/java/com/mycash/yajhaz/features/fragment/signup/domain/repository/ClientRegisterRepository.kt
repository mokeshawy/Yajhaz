package com.mycash.yajhaz.features.fragment.signup.domain.repository

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.signup.data.model.request.ClientRegisterRequestDto
import com.mycash.yajhaz.features.fragment.signup.data.model.response.ClientRegisterResponseDto
import kotlinx.coroutines.flow.Flow

interface ClientRegisterRepository {
    suspend fun signup(requestDto: ClientRegisterRequestDto): Flow<State<ClientRegisterResponseDto>>
}