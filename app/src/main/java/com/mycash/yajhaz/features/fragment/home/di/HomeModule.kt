package com.mycash.yajhaz.features.fragment.home.di

import com.mycash.yajhaz.core.di.service.HomeServices
import com.mycash.yajhaz.features.fragment.home.data.repository.base_categories_repository.BaseCategoriesRepositoryImpl
import com.mycash.yajhaz.features.fragment.home.data.repository.popular_sellers_repository.PopularSellersRepositoryImpl
import com.mycash.yajhaz.features.fragment.home.data.repository.trending_sellers_repository.TrendingSellersRepositoryImpl
import com.mycash.yajhaz.features.fragment.home.domain.repository.base_categories_repository.BaseCategoriesRepository
import com.mycash.yajhaz.features.fragment.home.domain.repository.popular_sellers_repository.PopularSellersRepository
import com.mycash.yajhaz.features.fragment.home.domain.repository.trending_sellers_repository.TrendingSellersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideBaseCategoriesRepository(homeServices: HomeServices): BaseCategoriesRepository =
        BaseCategoriesRepositoryImpl(homeServices)


    @Provides
    fun providePopularSellersRepository(homeServices: HomeServices): PopularSellersRepository =
        PopularSellersRepositoryImpl(homeServices)


    @Provides
    fun provideTrendingSellersRepositoryImpl(homeServices: HomeServices): TrendingSellersRepository =
        TrendingSellersRepositoryImpl(homeServices)
}