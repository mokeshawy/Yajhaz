package com.mycash.yajhaz.features.fragment.login.domain.model.entities


import com.google.gson.annotations.SerializedName

data class LoginEntity(
    val addresses: List<AddressEntity>,
    val balance: String,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val status: Int,
    val token: String,
    val type: Int
)