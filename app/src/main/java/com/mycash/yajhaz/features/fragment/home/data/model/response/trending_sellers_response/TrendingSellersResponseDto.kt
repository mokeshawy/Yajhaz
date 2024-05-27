package com.mycash.yajhaz.features.fragment.home.data.model.response.trending_sellers_response


import com.google.gson.annotations.SerializedName
import com.mycash.yajhaz.features.fragment.home.data.model.response.common.TrendingAndPopularSellersResponse

data class TrendingSellersResponseDto(
    @SerializedName("data")
    val data: List<TrendingAndPopularSellersResponse>,
    @SerializedName("message")
    val message: String,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("success")
    val success: Boolean
)