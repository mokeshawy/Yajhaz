package com.mycash.yajhaz.core.base_repository

import com.mycash.yajhaz.core.error.GeneralException
import com.mycash.yajhaz.core.error.IoException
import com.mycash.yajhaz.core.error.ResponseError
import com.mycash.yajhaz.core.error.ResponseUnAuthorizedError
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.state.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.Response
import java.util.concurrent.CancellationException


abstract class BaseRepository<RequestDto, ResponseDto> {

    var dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getOperationState(requestDto: RequestDto): State<ResponseDto> {
        return try {
            performApiCall(requestDto)
        } catch (e: IOException) {
            State.Error(getIoExceptionError(e))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            State.Error(getGeneralExceptionError(e))
        }
    }

    abstract suspend fun performApiCall(requestDto: RequestDto): State<ResponseDto>

    fun <T> getNotSuccessfulResponseState(response: Response<*>): State<T> {
        return when {
            response.code() == 401 -> State.Error(getUnauthorizedError(response))
            else -> State.Error(getNotSuccessfulResponseError(response))
        }
    }

    private fun getIoExceptionError(e: IOException) = YajhazError.E(
        exception = IoException(cause = e),
        logMessage = "Failed to load data from Api with IOException:",
    )

    private fun getGeneralExceptionError(e: Exception) = YajhazError.E(
        exception = GeneralException(cause = e),
        logMessage = "Failed to load data from Api with General exception",
    )

    private fun getNotSuccessfulResponseError(response: Response<*>) = YajhazError.E(
        exception = ResponseError(),
        logMessage = "Api request to url: ${response.raw().request.url}: failed with code ${response.code()}",
        extraData = response
    )

    private fun getUnauthorizedError(response: Response<*>) = YajhazError.E(
        exception = ResponseUnAuthorizedError(),
        logMessage = "Api request to url: ${response.raw().request.url}: failed with code ${response.code()}",
        extraData = response
    )
}