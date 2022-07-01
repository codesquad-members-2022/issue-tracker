package com.example.issu_tracker.data.network

sealed class NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>()
    class Cached<T : Any>(val data: T) : NetworkResult<T>()
    class Loading<T : Any> : NetworkResult<T>()
    class Error<T : Any>(val code: Int = EMPTY) : NetworkResult<T>()
    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>()
    companion object {
        const val EMPTY = 0
    }
}
