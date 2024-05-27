package com.mycash.yajhaz.features.fragment.home.domain.repository.trending_sellers_repository

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.trending_sellers_response.TrendingSellersResponseDto
import kotlinx.coroutines.flow.Flow

interface TrendingSellersRepository {

    suspend fun getTrendingSellers(requestDto: TrendingPopularSellersRequestDto): Flow<State<TrendingSellersResponseDto>>
}