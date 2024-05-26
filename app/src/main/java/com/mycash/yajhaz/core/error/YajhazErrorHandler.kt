package com.mycash.yajhaz.core.error

interface YajhazErrorHandler {
    fun handleError(error: YajhazError, callback: YajhazError.() -> Unit = {})
}