package com.mycash.yajhaz.core.token_utils

interface TokenHandler {

    fun saveToken(token: String)

    fun getToken(): String?

    fun getExpirationDate(): String

    fun clearToken()

    fun handleSaveToken(token: String)
}