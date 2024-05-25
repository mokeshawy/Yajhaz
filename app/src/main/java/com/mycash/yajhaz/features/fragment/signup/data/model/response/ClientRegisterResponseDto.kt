package com.mycash.yajhaz.features.fragment.signup.data.model.response


import com.google.gson.annotations.SerializedName

data class ClientRegisterResponseDto(
    @SerializedName("data")
    val data: ClientRegisterResponse,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)