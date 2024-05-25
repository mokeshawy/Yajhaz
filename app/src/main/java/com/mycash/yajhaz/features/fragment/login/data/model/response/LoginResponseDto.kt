package com.mycash.yajhaz.features.fragment.login.data.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("data")
    val data: LoginResponse,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)