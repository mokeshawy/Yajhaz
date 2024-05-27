package com.mycash.yajhaz.features.fragment.home.domain.usecase.trending_sellers_usecase

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.common.TrendingAndPopularSellersResponse
import com.mycash.yajhaz.features.fragment.home.domain.mappers.toTrendingPopularSellersEntity
import com.mycash.yajhaz.features.fragment.home.domain.repository.trending_sellers_repository.TrendingSellersRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class TrendingSellersUseCase @Inject constructor(private val trendingSellersRepository: TrendingSellersRepository) {

    private var trendingAndPopularSellersResponse: List<TrendingAndPopularSellersResponse>? = null


    suspend operator fun invoke(requestDto: TrendingPopularSellersRequestDto) = channelFlow {
        val response = async { trendingSellersRepository.getTrendingSellers(requestDto) }
        response.await().collect {
            if (it is State.Success) {
                trendingAndPopularSellersResponse = it.data?.data
            }
            send(it)
        }
    }

    fun getListOfTrendingSellers() =
        trendingAndPopularSellersResponse?.map { it.toTrendingPopularSellersEntity() }
            ?: emptyList()
}