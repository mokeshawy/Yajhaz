package com.mycash.yajhaz.features.fragment.login.domain.repository

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto
import com.mycash.yajhaz.features.fragment.login.data.model.response.LoginResponseDto
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(requestDto: LoginRequestDto): Flow<State<LoginResponseDto>>
}