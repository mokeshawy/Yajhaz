package com.mycash.yajhaz.features.fragment.home.domain.repository.popular_sellers_repository

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.popular_sellers_response.PopularSellersResponseDto
import kotlinx.coroutines.flow.Flow

interface PopularSellersRepository {

    suspend fun getPopularSellers(requestDto: TrendingPopularSellersRequestDto): Flow<State<PopularSellersResponseDto>>
}