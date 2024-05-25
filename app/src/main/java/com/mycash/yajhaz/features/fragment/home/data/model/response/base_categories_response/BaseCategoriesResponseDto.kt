package com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response


import com.google.gson.annotations.SerializedName

data class BaseCategoriesResponseDto(
    @SerializedName("data")
    val `data`: List<BaseCategoriesResponse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)