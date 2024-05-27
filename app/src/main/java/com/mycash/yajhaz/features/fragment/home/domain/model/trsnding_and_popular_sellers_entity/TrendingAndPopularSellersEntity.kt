package com.mycash.yajhaz.features.fragment.home.domain.model.trsnding_and_popular_sellers_entity


data class TrendingAndPopularSellersEntity(
    val address: String? = null,
    val id: Int,
    val image: String,
    val isFavorite: Boolean,
    val lat: String? = null,
    val lng: String? = null,
    val logo: String,
    val name: String,
    val rate: String,
    val type: Int
)