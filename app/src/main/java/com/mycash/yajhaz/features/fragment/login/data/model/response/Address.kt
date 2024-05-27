package com.mycash.yajhaz.features.fragment.login.data.model.response


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("apartment")
    val apartment: String,
    @SerializedName("building")
    val building: String,
    @SerializedName("floor")
    val floor: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("street")
    val street: String
)