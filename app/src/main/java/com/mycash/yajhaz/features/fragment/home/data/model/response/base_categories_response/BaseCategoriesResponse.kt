package com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response


import com.google.gson.annotations.SerializedName

data class BaseCategoriesResponse(
    @SerializedName("active")
    val active: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_ar")
    val nameAr: String,
    @SerializedName("name_en")
    val nameEn: String
)