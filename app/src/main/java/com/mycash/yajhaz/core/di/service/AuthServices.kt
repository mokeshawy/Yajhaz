package com.mycash.yajhaz.core.di.service

import com.mycash.yajhaz.features.fragment.login.data.model.request.LoginRequestDto
import com.mycash.yajhaz.features.fragment.login.data.model.response.LoginResponseDto
import com.mycash.yajhaz.features.fragment.signup.data.model.request.ClientRegisterRequestDto
import com.mycash.yajhaz.features.fragment.signup.data.model.response.ClientRegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthServices {

    @POST("login")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): Response<LoginResponseDto>


    @POST("client-register")
    suspend fun clientRegister(@Body clientRegisterRequestDto: ClientRegisterRequestDto): Response<ClientRegisterResponseDto>
}