package com.mycash.yajhaz.features.fragment.login.domain.usecase

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto
import com.mycash.yajhaz.features.fragment.login.domain.repository.LoginRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenHandler: TokenHandler
) {

    suspend operator fun invoke(requestDto: LoginRequestDto) = channelFlow {
        val response = async { loginRepository.login(requestDto) }
        response.await().collect {
            if (it is State.Success) {
                val token = it.data?.data?.token ?: ""
                tokenHandler.handleSaveToken(token)
            }
            send(it)
        }
    }
}