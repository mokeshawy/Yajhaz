package com.mycash.yajhaz.features.fragment.home.data.model.response.common


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("active")
    val active: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)