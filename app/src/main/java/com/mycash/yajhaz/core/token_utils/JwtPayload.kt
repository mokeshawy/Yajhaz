package com.mycash.yajhaz.core.token_utils


import com.google.gson.annotations.SerializedName

data class JwtPayload(
    @SerializedName("aud")
    val aud: String,
    @SerializedName("exp")
    val exp: Double,
    @SerializedName("iat")
    val iat: Double,
    @SerializedName("jti")
    val jti: String,
    @SerializedName("nbf")
    val nbf: Double,
    @SerializedName("scopes")
    val scopes: List<Any>,
    @SerializedName("sub")
    val sub: String
)