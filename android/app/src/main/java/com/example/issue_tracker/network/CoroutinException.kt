package com.example.issue_tracker.network

import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

object CoroutineException {
    fun throwableCheck(throwable: Throwable): CEHModel {
        return when (throwable) {
            is SocketException -> CEHModel(throwable, "네트워크 연결이 끊겼습니다.")
            is HttpException -> CEHModel(throwable, "Http 관련 오류입니다")
            is UnknownHostException -> CEHModel(throwable, "UnknownHost 오류입니다.")
            else -> CEHModel(throwable, "알 수 없는 오류입니다.")
        }
    }
}