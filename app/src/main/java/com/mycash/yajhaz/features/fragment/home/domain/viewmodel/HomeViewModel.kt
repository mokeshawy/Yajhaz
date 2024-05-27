package com.mycash.yajhaz.features.fragment.home.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponseDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.popular_sellers_response.PopularSellersResponseDto
import com.mycash.yajhaz.features.fragment.home.data.model.response.trending_sellers_response.TrendingSellersResponseDto
import com.mycash.yajhaz.features.fragment.home.domain.usecase.base_categories_usecase.BaseCategoriesUseCase
import com.mycash.yajhaz.features.fragment.home.domain.usecase.popular_sellers_usecase.PopularSellersUseCase
import com.mycash.yajhaz.features.fragment.home.domain.usecase.trending_sellers_usecase.TrendingSellersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val baseCategoriesUseCase: BaseCategoriesUseCase,
    private val popularSellersUseCase: PopularSellersUseCase,
    private val trendingSellersUseCase: TrendingSellersUseCase
) : ViewModel() {


    private val _baseCategoriesResponseState =
        MutableStateFlow<State<BaseCategoriesResponseDto>>(State.Initial())
    val baseCategoriesResponseState = _baseCategoriesResponseState.asStateFlow()

    private val _popularSellersResponseState =
        MutableStateFlow<State<PopularSellersResponseDto>>(State.Initial())
    val popularSellersResponseState = _popularSellersResponseState.asStateFlow()

    private val _trendingSellersResponseState =
        MutableStateFlow<State<TrendingSellersResponseDto>>(State.Initial())
    val trendingSellersResponseState = _trendingSellersResponseState.asStateFlow()


    fun getAllResult(requestDto: TrendingPopularSellersRequestDto) =
        viewModelScope.launch {
            emittedLoading()
            val baseCategories = async { baseCategoriesUseCase() }
            val popularSellers = async { popularSellersUseCase(requestDto) }
            val trendingSellers = async { trendingSellersUseCase(requestDto) }
            val response = awaitAll(baseCategories, popularSellers, trendingSellers)
            response.collectOnAllResult()
        }

    private suspend fun emittedLoading() {
        _baseCategoriesResponseState.emit(State.Loading())
        _popularSellersResponseState.emit(State.Loading())
        _trendingSellersResponseState.emit(State.Loading())
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun List<Flow<State<out Any?>>>.collectOnAllResult() {
        this[0].collect { _baseCategoriesResponseState.emit(it as State<BaseCategoriesResponseDto>) }
        this[1].collect { _popularSellersResponseState.emit(it as State<PopularSellersResponseDto>) }
        this[2].collect { _trendingSellersResponseState.emit(it as State<TrendingSellersResponseDto>) }
    }


    fun getListOfBaseCategories() = baseCategoriesUseCase.getListOfBaseCategories()

    fun getListOfPopularSellers() = popularSellersUseCase.getListOfPopularSellers()

    fun getListOfTrendingSellers() = trendingSellersUseCase.getListOfTrendingSellers()
}