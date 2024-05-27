package com.mycash.yajhaz.features.fragment.home.domain.usecase.popular_sellers_usecase

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.common.TrendingAndPopularSellersResponse
import com.mycash.yajhaz.features.fragment.home.domain.mappers.toTrendingPopularSellersEntity
import com.mycash.yajhaz.features.fragment.home.domain.repository.popular_sellers_repository.PopularSellersRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class PopularSellersUseCase @Inject constructor(private val popularSellersRepository: PopularSellersRepository) {


    private var trendingAndPopularSellersResponse: List<TrendingAndPopularSellersResponse>? = null


    suspend operator fun invoke(requestDto: TrendingPopularSellersRequestDto) = channelFlow {
        val response = async { popularSellersRepository.getPopularSellers(requestDto) }
        response.await().collect {
            if (it is State.Success) {
                trendingAndPopularSellersResponse = it.data?.data
            }
            send(it)
        }
    }

    fun getListOfPopularSellers() =
        trendingAndPopularSellersResponse?.map { it.toTrendingPopularSellersEntity() }
            ?: emptyList()
}