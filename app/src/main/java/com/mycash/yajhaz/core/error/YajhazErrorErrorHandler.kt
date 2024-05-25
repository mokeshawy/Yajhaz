package com.mycash.yajhaz.core.error

interface YajhazErrorErrorHandler {
    fun handleError(error: YajhazError, callback: YajhazError.() -> Unit = {})
}