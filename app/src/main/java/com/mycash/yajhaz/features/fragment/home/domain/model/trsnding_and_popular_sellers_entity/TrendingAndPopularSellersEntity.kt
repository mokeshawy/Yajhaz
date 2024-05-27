package com.mycash.yajhaz.features.fragment.home.domain.model.trsnding_and_popular_sellers_entity


data class TrendingAndPopularSellersEntity(
    val address: String,
    val id: Int,
    val image: String,
    val isFavorite: Boolean,
    val lat: String,
    val lng: String,
    val logo: String,
    val name: String,
    val rate: String,
    val type: Int
)