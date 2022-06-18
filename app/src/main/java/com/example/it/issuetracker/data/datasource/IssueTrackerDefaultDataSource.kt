package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.MileStone

class IssueTrackerDefaultDataSource : IssueTrackerDataSource {

    private val issues: MutableList<IssueDto> = mutableListOf(
        IssueDto(
            id = 1L,
            title = "제목",
            description = "이슈에 대한 설명(두 줄까지 보여줄 수 있다)",
            state = true,
            label = listOf(
                Label(name = "Label", color = "#D5D5DB", textColor = "#000000"),
            ),
            mileStone = MileStone(name = "마일스톤")
        ),
        IssueDto(
            id = 2L,
            title = "안드로이드이슈트래커",
            description = "2022년 6월 13일 월요일 부터 7월 1일 금요일 까지",
            state = true,
            label = listOf(
                Label(name = "Documentation", color = "#020070", textColor = "#FFFFFF")
            ),
            mileStone = MileStone(name = "마스터즈 코스")
        ),
        IssueDto(
            id = 3L,
            title = "테스트",
            description = "테스트 설명",
            state = false,
            label = listOf(
                Label(name = "test", color = "#020310", textColor = "#FFFFFF")
            ),
            mileStone = MileStone(name = "테스트 그룹")
        ),
        IssueDto(
            id = 4L,
            title = "안드로이드이슈트래커",
            description = "123456789",
            state = true,
            label = listOf(
                Label(name = "Number", color = "#020421", textColor = "#FFFFFF")
            ),
            mileStone = MileStone(name = "마스터즈 코스 숫자")
        )
    )

    override suspend fun getIssue(): Result<List<IssueDto>> {
        return runCatching { issues.filter { it.state } }
    }

    override suspend fun deleteIssue(list: List<Issue>): Result<List<IssueDto>> {
        list.forEach { issue ->
            val removeIssue = issues.find { it.id == issue.id }
            issues.remove(removeIssue)
        }
        return getIssue()
    }

    override suspend fun closeIssue(list: List<Issue>): Result<List<IssueDto>> {
        list.forEach { issue ->
            val closeIssue = issues.find { it.id == issue.id }
            val index = issues.indexOf(closeIssue)
            issues[index].state = false
        }
        return getIssue()
    }
}