package com.example.issue_tracker.common

sealed class ResponseResult<out T: Any> {
    data class Success<out T: Any>(val data: T): ResponseResult<T>()
    data class Error(val error: String): ResponseResult<Nothing>()
}
