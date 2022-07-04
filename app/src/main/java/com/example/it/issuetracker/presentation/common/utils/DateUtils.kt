package com.example.it.issuetracker.presentation.common.utils

import java.time.Duration
import java.util.*

enum class TimeValue(val value: Int, val maximum: Int, val message: String) {
    SEC(60, 60, TimeValue.MIN_AGO),
    MIN(60, 24, TimeValue.TIME_AGO),
    HOUR(24, 30, TimeValue.DAY_AGO),
    DAY(30, 12, TimeValue.MONTH_AGO),
    MONTH(12, Int.MAX_VALUE, TimeValue.YEAR_AGO);

    companion object {
        const val SECOND_AGO = "초 전"
        const val MIN_AGO = "분 전"
        const val TIME_AGO = "시간 전"
        const val DAY_AGO = "일 전"
        const val MONTH_AGO = "달 전"
        const val YEAR_AGO = "년 전"
    }
}

/**
 * @param : 날짜 타입
 * @return : 현재 시간부로 얼마나 지났는지 반환
 *           (초 전, 분 전, 시간 전, 일 전, 달 전, 년 전)에 해당하지 안하면 null
 */
fun calculateTime(date: Date): String? {
    val currentTime = System.currentTimeMillis()
    val registerTime = date.time
    var differenceTime = (currentTime - registerTime) / 1000

    if (differenceTime < TimeValue.SEC.value) {
        return "${differenceTime}${TimeValue.SECOND_AGO}"
    } else {
        for (timeValue in TimeValue.values()) {
            differenceTime /= timeValue.value
            if (differenceTime < timeValue.maximum) {
                return "${differenceTime}${timeValue.message}"
            }
        }
    }
    return null
}