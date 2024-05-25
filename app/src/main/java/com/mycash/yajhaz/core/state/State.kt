package com.mycash.yajhaz.core.state

import com.mycash.yajhaz.core.error.YajhazError


sealed class State<T> {

    class Initial<T> : State<T>()
    class Loading<T> : State<T>()
    data class Success<T>(val data: T? = null, val error: YajhazError? = null) : State<T>()
    data class Error<T>(val error: YajhazError) : State<T>()

}