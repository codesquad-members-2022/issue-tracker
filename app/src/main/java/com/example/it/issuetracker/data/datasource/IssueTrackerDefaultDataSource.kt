package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.MileStone

class IssueTrackerDefaultDataSource : IssueTrackerDataSource {

    private val issues: List<Issue> = listOf(
        Issue(
            title = "제목",
            description = "이슈에 대한 설명(두 줄까지 보여줄 수 있다)",
            label = listOf(
                Label(id = 1, title = "Label", color = "#D5D5DB", textColor = "#000000"),
            ),
            mileStone = MileStone(name = "마일스톤")
        ),
        Issue(
            title = "안드로이드이슈트래커",
            description = "2022년 6월 13일 월요일 부터 7월 1일 금요일 까지",
            label = listOf(
                Label(id = 2, title = "Documentation", color = "#020070", textColor = "#FFFFFF")
            ),
            mileStone = MileStone(name = "마스터즈 코스")
        )
    )

    override suspend fun getIssue(): Result<List<Issue>> {
        return runCatching { issues }
    }
}