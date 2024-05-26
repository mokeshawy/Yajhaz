package com.mycash.yajhaz.core.error

import timber.log.Timber

sealed class YajhazError {
    var exception: Throwable? = GeneralException()
    var extraData: Any? = null
    var logMessage: String? = null
    var logTag: String = "Yabraa Error"
    var logPriority: ErrorLogPriority = ErrorLogPriority.ERROR

    fun logError() {
        val message = logTag +
                "/ Log message: $logMessage" +
                "/ Extra Data: $extraData"
        Timber.log(logPriority.level, exception, message)
    }


    class E(
        exception: Exception,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.ERROR
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class W(
        exception: Exception,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.WARN
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class I(
        exception: Exception,
        logMessage: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.INFO
            this.logMessage = logMessage
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }
}