package com.mycash.yajhaz.features.fragment.home.data.model.response.popular_sellers_response


import com.google.gson.annotations.SerializedName

data class PopularResponseDto(
    @SerializedName("data")
    val `data`: List<PopularResponse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)