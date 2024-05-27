package com.mycash.yajhaz.features.fragment.home.data.repository.base_categories_repository

import com.mycash.yajhaz.core.base_repository.BaseRepository
import com.mycash.yajhaz.core.di.service.HomeServices
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.core.utils.getResponseMessageError
import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponseDto
import com.mycash.yajhaz.features.fragment.home.domain.repository.base_categories_repository.BaseCategoriesRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class BaseCategoriesRepositoryImpl @Inject constructor(private val homeServices: HomeServices) :
    BaseRepository<Any, BaseCategoriesResponseDto>(), BaseCategoriesRepository {


    override suspend fun getBaseCategories() = flow {
        emit(getOperationState(Any()))
    }.flowOn(dispatcher)

    override suspend fun performApiCall(requestDto: Any): State<BaseCategoriesResponseDto> {
        val response = homeServices.baseCategories()
        return handleBaseCategoriesResponseSuccessState(response)
    }

    private fun handleBaseCategoriesResponseSuccessState(response: Response<BaseCategoriesResponseDto>): State<BaseCategoriesResponseDto> {
        val data = response.body()
        return when (data?.success) {
            true -> State.Success(data)
            false -> getResponseMessageError(data.message, data.message)
            else -> getNotSuccessfulResponseState(response)
        }
    }
}