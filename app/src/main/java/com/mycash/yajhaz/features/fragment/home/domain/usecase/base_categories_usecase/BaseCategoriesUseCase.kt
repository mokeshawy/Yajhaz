package com.mycash.yajhaz.features.fragment.home.domain.usecase.base_categories_usecase

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponse
import com.mycash.yajhaz.features.fragment.home.domain.mappers.toBaseCategoriesEntity
import com.mycash.yajhaz.features.fragment.home.domain.repository.base_categories_repository.BaseCategoriesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class BaseCategoriesUseCase @Inject constructor(private val baseCategoriesRepository: BaseCategoriesRepository) {

    private var baseCategoriesResponse: List<BaseCategoriesResponse>? = null


    suspend operator fun invoke() = channelFlow {
        val response = async { baseCategoriesRepository.getBaseCategories() }
        response.await().collect {
            if (it is State.Success) {
                baseCategoriesResponse = it.data?.data
            }
            send(it)
        }
    }

    fun getListOfBaseCategories() = baseCategoriesResponse?.map { it.toBaseCategoriesEntity() }
}