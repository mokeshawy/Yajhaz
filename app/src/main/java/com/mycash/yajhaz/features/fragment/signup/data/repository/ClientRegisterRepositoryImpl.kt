package com.mycash.yajhaz.features.fragment.signup.data.repository

import com.mycash.yajhaz.core.base_repository.BaseRepository
import com.mycash.yajhaz.core.di.service.AuthServices
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.getResponseMessageError
import com.mycash.yajhaz.features.fragment.signup.data.model.request.ClientRegisterRequestDto
import com.mycash.yajhaz.features.fragment.signup.data.model.response.ClientRegisterResponseDto
import com.mycash.yajhaz.features.fragment.signup.domain.repository.ClientRegisterRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ClientRegisterRepositoryImpl @Inject constructor(private val authServices: AuthServices) :
    BaseRepository<ClientRegisterRequestDto, ClientRegisterResponseDto>(),
    ClientRegisterRepository {

    override suspend fun signup(requestDto: ClientRegisterRequestDto) = flow {
        emit(getOperationState(requestDto))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: ClientRegisterRequestDto): State<ClientRegisterResponseDto> {
        val response = authServices.clientRegister(requestDto)
        return handleClientRegisterResponseSuccessState(response)
    }

    private fun handleClientRegisterResponseSuccessState(response: Response<ClientRegisterResponseDto>): State<ClientRegisterResponseDto> {
        val data = response.body()
        return when (data?.success) {
            true -> State.Success(data)
            false -> getResponseMessageError(data.message, data.message)
            else -> getNotSuccessfulResponseState(response)
        }
    }
}