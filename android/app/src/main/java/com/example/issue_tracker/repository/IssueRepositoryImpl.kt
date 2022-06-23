package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.model.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(): IssueRepository {

    override suspend fun getIssue(): Flow<MutableList<Issue>> {
        return flow {
            emit(getTempDummyData())
        }
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
        val secondIssue3 = Issue(
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
        val secondIssue4 = Issue(
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
        val secondIssue5 = Issue(
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
        val secondIssue6 = Issue(
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
        val secondIssue7 = Issue(
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
        val secondIssue8 = Issue(
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
        return mutableListOf(firstIssue, secondIssue, secondIssue2, secondIssue3, secondIssue4, secondIssue5, secondIssue6, secondIssue7, secondIssue8)
    }
}