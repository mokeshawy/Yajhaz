package com.mycash.yajhaz.features.fragment.login.domain.usecase

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto
import com.mycash.yajhaz.features.fragment.login.data.model.response.Address
import com.mycash.yajhaz.features.fragment.login.data.model.response.LoginResponse
import com.mycash.yajhaz.features.fragment.login.domain.model.entities.AddressEntity
import com.mycash.yajhaz.features.fragment.login.domain.model.entities.LoginEntity
import com.mycash.yajhaz.features.fragment.login.domain.repository.LoginRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenHandler: TokenHandler
) {

    private var loginResponse: LoginResponse? = null

    suspend operator fun invoke(requestDto: LoginRequestDto) = channelFlow {
        val response = async { loginRepository.login(requestDto) }
        response.await().collect {
            if (it is State.Success) {
                val token = it.data?.data?.token ?: ""
                tokenHandler.handleSaveToken(token)
                loginResponse = it.data?.data
            }
            send(it)
        }
    }


    fun getLoginEntity() = loginResponse?.toLoginEntity()

    private fun LoginResponse.toLoginEntity() = LoginEntity(
        addresses = addresses.map { it.toAddressEntity() },
        balance = balance,
        email = email,
        id = id,
        image = image,
        name = name,
        phone = phone,
        status = status,
        token = token,
        type = type,
    )

    private fun Address.toAddressEntity() = AddressEntity(
        address = address,
        apartment = apartment,
        building = building,
        floor = floor,
        id = id,
        lat = lat,
        lng = lng,
        name = name,
        street = street,
    )
}