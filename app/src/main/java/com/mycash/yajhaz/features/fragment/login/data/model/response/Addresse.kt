package com.mycash.yajhaz.features.fragment.login.data.model.response


import com.google.gson.annotations.SerializedName

data class Addresse(
    @SerializedName("address")
    val address: Any,
    @SerializedName("apartment")
    val apartment: String,
    @SerializedName("building")
    val building: String,
    @SerializedName("floor")
    val floor: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("name")
    val name: Any,
    @SerializedName("street")
    val street: String
)