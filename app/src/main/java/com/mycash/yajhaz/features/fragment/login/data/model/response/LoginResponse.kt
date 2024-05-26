package com.mycash.yajhaz.features.fragment.login.data.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("addresses")
    val addresses: List<Address>,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: Int
)