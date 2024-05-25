package com.mycash.yajhaz.core.error

import androidx.annotation.Keep


open class YabraaErrorException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)


@Keep
class GeneralException(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class IoException(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class ResponseError(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class ResponseUnAuthorizedError(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)


@Keep
class FailedToDecodeJwt(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class RefreshTokenNotFound(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class InvalidEmail(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)


@Keep
class EmptyName(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)


@Keep
class EmptyPassword(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class PasswordLetThanSixCharacter(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class OperationMessage(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class EmptyPhoneNumber(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)

@Keep
class EmptyConfirmPassword(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)
@Keep
class PasswordNotMatched(message: String? = null, cause: Throwable? = null) : YabraaErrorException(message, cause)