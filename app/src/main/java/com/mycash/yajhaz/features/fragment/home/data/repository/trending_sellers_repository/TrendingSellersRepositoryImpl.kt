package com.mycash.yajhaz.features.fragment.home.data.repository.trending_sellers_repository

import com.mycash.yajhaz.core.base_repository.BaseRepository
import com.mycash.yajhaz.core.di.service.HomeServices
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.getResponseMessageError
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.trending_sellers_response.TrendingSellersResponseDto
import com.mycash.yajhaz.features.fragment.home.domain.repository.trending_sellers_repository.TrendingSellersRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class TrendingSellersRepositoryImpl @Inject constructor(private val homeServices: HomeServices) :
    BaseRepository<TrendingPopularSellersRequestDto, TrendingSellersResponseDto>(),
    TrendingSellersRepository {


    override suspend fun getTrendingSellers(requestDto: TrendingPopularSellersRequestDto) = flow {
        emit(getOperationState(requestDto))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: TrendingPopularSellersRequestDto): State<TrendingSellersResponseDto> {
        val response =
            homeServices.trendingSellers(requestDto.lat, requestDto.lng, requestDto.filter)
        return handleTrendingSellersResponseSuccessState(response)
    }

    private fun handleTrendingSellersResponseSuccessState(response: Response<TrendingSellersResponseDto>): State<TrendingSellersResponseDto> {
        val data = response.body()
        return when (data?.success) {
            true -> State.Success(data)
            false -> getResponseMessageError(data.message, data.message)
            else -> getNotSuccessfulResponseState(response)
        }
    }
}