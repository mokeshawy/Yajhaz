package com.mycash.yajhaz.core.utils

import com.mycash.yajhaz.core.error.OperationMessage
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State

fun <T> getResponseMessageError(errorMessage: String, logTag: String? = null) = State.Error<T>(
    YajhazError.E(exception = OperationMessage(), logMessage = errorMessage, logTag = logTag)
)