package com.mycash.yajhaz.features.fragment.home.domain.mappers

import com.mycash.yajhaz.features.fragment.home.data.model.response.base_categories_response.BaseCategoriesResponse
import com.mycash.yajhaz.features.fragment.home.data.model.response.common.TrendingAndPopularSellersResponse
import com.mycash.yajhaz.features.fragment.home.domain.model.base_category_entity.BaseCategoriesEntity
import com.mycash.yajhaz.features.fragment.home.domain.model.trsnding_and_popular_sellers_entity.TrendingAndPopularSellersEntity


fun TrendingAndPopularSellersResponse.toTrendingPopularSellersEntity() =
    TrendingAndPopularSellersEntity(
        address = address,
        id = id,
        image = image,
        isFavorite = isFavorite,
        lat = lat,
        lng = lng,
        logo = logo,
        name = name,
        rate = rate,
        type = type
    )


fun BaseCategoriesResponse.toBaseCategoriesEntity() = BaseCategoriesEntity(
    active = active,
    id = id,
    image = image,
    name = name,
    nameAr = nameAr,
    nameEn = nameEn
)