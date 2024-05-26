package com.mycash.yajhaz.features.fragment.login.data.repository

import com.mycash.yajhaz.core.base_repository.BaseRepository
import com.mycash.yajhaz.core.di.service.AuthServices
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.getResponseMessageError
import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto
import com.mycash.yajhaz.features.fragment.login.data.model.response.LoginResponseDto
import com.mycash.yajhaz.features.fragment.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val authServices: AuthServices) :
    BaseRepository<LoginRequestDto, LoginResponseDto>(), LoginRepository {

    override suspend fun login(requestDto: LoginRequestDto) = flow {
        emit(getOperationState(requestDto))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: LoginRequestDto): State<LoginResponseDto> {
        val response = authServices.login(requestDto)
        return handleLoginResponseSuccessState(response)
    }

    private fun handleLoginResponseSuccessState(response: Response<LoginResponseDto>): State<LoginResponseDto> {
        val data = response.body()
        return when (data?.success) {
            true -> State.Success(data)
            false -> getResponseMessageError(data.message, data.message)
            else -> getNotSuccessfulResponseState(response)
        }
    }
}