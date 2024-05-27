package com.mycash.yajhaz.core.di.service

import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponseDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.popular_sellers_response.PopularSellersResponseDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.trending_sellers_response.TrendingSellersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServices {

    @GET("popular-sellers")
    suspend fun popularSellers(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): Response<PopularSellersResponseDto>


    @GET("trending-sellers")
    suspend fun trendingSellers(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("filter") filter: Int
    ): Response<TrendingSellersResponseDto>


    @GET("base-categories")
    suspend fun baseCategories(): Response<BaseCategoriesResponseDto>

}