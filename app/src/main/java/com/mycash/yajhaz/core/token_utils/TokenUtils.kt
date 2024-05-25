package com.mycash.yajhaz.core.token_utils

import com.google.android.gms.common.util.Base64Utils
import com.google.gson.Gson
import com.mycash.yajhaz.core.error.FailedToDecodeJwt
import com.mycash.yajhaz.core.error.YajhazError
import java.nio.charset.StandardCharsets


fun String.getPayLoad(onError: (YajhazError) -> Unit = {}): JwtPayload? {
    val jsonPayload = getJsonPayload(onError)
    return Gson().fromJson(jsonPayload, JwtPayload::class.java)
}

private fun String.getJsonPayload(onError: (YajhazError) -> Unit = {}): String? {
    val parts = split(".").toTypedArray()
    return try {
        Base64Utils.decodeUrlSafe(parts[1]).toString(StandardCharsets.UTF_8)
    } catch (e: Exception) {
        onError(getJwtError(e))
        null
    }
}

private fun String.getJwtError(e: Exception): YajhazError {
    return YajhazError.E(
        exception = FailedToDecodeJwt(cause = e),
        logMessageEn = "failed to decode JWT for token: $this",
        logTag = TokensManager::class.java.simpleName
    )
}