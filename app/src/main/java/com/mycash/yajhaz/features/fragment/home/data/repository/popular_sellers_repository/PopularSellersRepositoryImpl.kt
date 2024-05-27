package com.mycash.yajhaz.features.fragment.home.data.repository.popular_sellers_repository

import com.mycash.yajhaz.core.base_repository.BaseRepository
import com.mycash.yajhaz.core.di.service.HomeServices
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.getResponseMessageError
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.popular_sellers_response.PopularSellersResponseDto
import com.mycash.yajhaz.features.fragment.home.domain.repository.popular_sellers_repository.PopularSellersRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class PopularSellersRepositoryImpl @Inject constructor(private val homeServices: HomeServices) :
    BaseRepository<TrendingPopularSellersRequestDto, PopularSellersResponseDto>(),
    PopularSellersRepository {


    override suspend fun getPopularSellers(requestDto: TrendingPopularSellersRequestDto) = flow {
        emit(getOperationState(requestDto))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: TrendingPopularSellersRequestDto): State<PopularSellersResponseDto> {
        val response =
            homeServices.popularSellers(requestDto.lat, requestDto.lng, requestDto.filter)
        return handlePopularSellersResponseSuccessState(response)
    }

    private fun handlePopularSellersResponseSuccessState(response: Response<PopularSellersResponseDto>): State<PopularSellersResponseDto> {
        val data = response.body()
        return when (data?.success) {
            true -> State.Success(data)
            false -> getResponseMessageError(data.message, data.message)
            else -> getNotSuccessfulResponseState(response)
        }
    }
}