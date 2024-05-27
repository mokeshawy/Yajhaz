package com.mycash.yajhaz.features.fragment.home.domain.repository.base_categories_repository

import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponseDto
import kotlinx.coroutines.flow.Flow

interface BaseCategoriesRepository {

    suspend fun getBaseCategories(): Flow<State<BaseCategoriesResponseDto>>
}