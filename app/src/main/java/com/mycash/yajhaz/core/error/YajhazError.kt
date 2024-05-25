package com.mycash.yajhaz.core.error

import timber.log.Timber

sealed class YajhazError {
    var exception: Throwable? = GeneralException()
    var extraData: Any? = null
    var logMessageEn: String? = null
    var logMessageAr: String? = null
    var logTag: String = "Yabraa Error"
    var logPriority: ErrorLogPriority = ErrorLogPriority.ERROR

    fun logError() {
        val message = logTag +
                "/ Log messageEn: $logMessageEn" +
                "/ Log messageAr: $logMessageAr" +
                "/ Extra Data: $extraData"
        Timber.log(logPriority.level, exception, message)
    }


    class E(
        exception: Exception,
        logMessageEn: String? = null,
        logMessageAr: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.ERROR
            this.logMessageEn = logMessageEn
            this.logMessageAr = logMessageAr
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class W(
        exception: Exception,
        logMessageEn: String? = null,
        logMessageAr: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.WARN
            this.logMessageEn = logMessageEn
            this.logMessageAr = logMessageAr
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }

    class I(
        exception: Exception,
        logMessageEn: String? = null,
        logMessageAr: String? = null,
        logTag: String? = null,
        extraData: Any? = null
    ) : YajhazError() {
        init {
            this.logPriority = ErrorLogPriority.INFO
            this.logMessageEn = logMessageEn
            this.logMessageAr = logMessageAr
            logTag?.let { this.logTag = it }
            this.exception = exception
            this.extraData = extraData
        }

    }
}