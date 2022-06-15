package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Issue

class HomeRepositoryImpl : HomeRepository {
    override fun loadIssues(): List<Issue> {
        // TODO 추후 수정 예정
        return listOf(
            Issue(0, "마일스톤", "Document", "그라운드 룰", "Issue_tracker 의 그라운드 룰을 정의합니다."),
            Issue(1, "마일스톤", "Feature", "리사이클러뷰 구현", "리사이클러뷰를 구현했습니다."),
        )
    }
}