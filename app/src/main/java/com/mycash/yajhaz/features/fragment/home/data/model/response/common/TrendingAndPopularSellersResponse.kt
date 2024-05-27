package com.mycash.yajhaz.features.fragment.home.data.model.response.common


import com.google.gson.annotations.SerializedName

data class TrendingAndPopularSellersResponse(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("appointments")
    val appointments: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("description")
    val description: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("information")
    val information: Information,
    @SerializedName("is_favorite")
    val isFavorite: Boolean,
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val lng: String? = null,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("popular")
    val popular: Int,
    @SerializedName("product_categories")
    val productCategories: List<ProductCategory>,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("trending")
    val trending: Int,
    @SerializedName("type")
    val type: Int
)