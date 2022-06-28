package com.example.issue_tracker.repository

import android.util.Log
import com.example.issue_tracker.common.ResponseResult
import com.example.issue_tracker.common.toClientIssue
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.LabelDTO
import com.example.issue_tracker.network.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(private val apiService: APIService): IssueRepository {

    // 테스트용 Issue 더미데이터
    override suspend fun getDummyIssue(): MutableList<Issue> {
        return getTempDummyData()
    }

    // 실제 서버에서 가져올 Issue 데이터
    override suspend fun getIssue(): ResponseResult<MutableList<Issue>> {
        
        coroutineScope {
            launch {
                val response = apiService.getIssues().issues.toClientIssue()
                Log.d("Repository", response.toString())
            }
        }
        val response = apiService.getIssues().issues.toClientIssue()
        return if (response.isNotEmpty()) ResponseResult.Success(response)
        else ResponseResult.Error("이슈를 불러오지 못했습니다.")
    }

    // 테스트를 위한 더미 데이터
    private fun getTempDummyData(): MutableList<Issue> {
        val firstIssue = Issue(
            1,
            "마스터즈 코스",
            "안드로이드 이슈트래커",
            "2022년 6월 13일 월요일 부터 7월 1일 금요일 까지 2022년 6월 13일 월요일 부터 7월 1일 금요일 까지",
            Label(
                1,
                "feature",
                "Documentation",
                "#020070"
            )
        )
        val secondIssue = Issue(
            2,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                2,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue2 = Issue(
            3,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                3,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue3 = Issue(
            4,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                4,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue4 = Issue(
            5,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                5,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue5 = Issue(
            6,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                6,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue6 = Issue(
            7,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                7,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue7 = Issue(
            8,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                8,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        val secondIssue8 = Issue(
            9,
            "코코아 코스",
            "ios 이슈트래커",
            "2022년 8월 12일 월요일 부터 10월 1일 금요일 까지",
            Label(
                9,
                "fix",
                "fix",
                "#D5D5DB"
            )
        )
        return mutableListOf(firstIssue, secondIssue, secondIssue2, secondIssue3, secondIssue4, secondIssue5, secondIssue6, secondIssue7, secondIssue8)
    }
}