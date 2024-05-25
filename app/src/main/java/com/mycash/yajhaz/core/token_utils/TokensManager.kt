package com.mycash.yajhaz.core.token_utils

import com.mycash.yajhaz.core.storage_manager.StorageManager
import com.mycash.yajhaz.core.storage_manager.di.StorageManagerModule.ENCRYPTED_SHARED_PREFERENCE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

const val TOKEN_KEY = "TOKEN_KEY"

class TokensManager @Inject constructor(
    @Named(ENCRYPTED_SHARED_PREFERENCE)
    private val encryptedSharedPreference: StorageManager
) : TokenHandler {

    override fun saveToken(token: String) = encryptedSharedPreference.setString(TOKEN_KEY, token)

    override fun getToken() = encryptedSharedPreference.getString(TOKEN_KEY)
    override fun getExpirationDate(): String {
        val epoch = getToken()?.getPayLoad()?.exp ?: 0L
        val date = Date(epoch * 1000)
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    override fun clearToken() = encryptedSharedPreference.remove(TOKEN_KEY)


    override fun handleSaveToken(token: String) {
        val oldToken = getToken()?.getPayLoad()?.sub
        val newToken = token.getPayLoad()?.sub ?: ""
        if (newToken == oldToken) return
        clearToken()
        saveToken(token)
    }

}