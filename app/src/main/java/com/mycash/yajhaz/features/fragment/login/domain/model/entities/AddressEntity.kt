package com.mycash.yajhaz.features.fragment.login.domain.model.entities


data class AddressEntity(
    val address: String,
    val apartment: String,
    val building: String,
    val floor: Int,
    val id: Int,
    val lat: Double,
    val lng: Double,
    val name: String,
    val street: String
)