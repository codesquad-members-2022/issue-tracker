package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.MileStone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class IssueTrackerDefaultDataSource : IssueTrackerDataSource {

    private val issues: MutableList<IssueDto> = mutableListOf(
        IssueDto(
            id = 1L,
            title = "제목",
            description = "이슈에 대한 설명(두 줄까지 보여줄 수 있다)",
            state = true,
            label = listOf(LabelFakeDatabase.database[0]),
            mileStone = MileStone(id = 1, title = "마일스톤", deadLine = "2022-06-14"),
            createdTime = "2022-05-21",
            milestoneId = 1,
            memberId = 1
        ),
        IssueDto(
            id = 2L,
            title = "안드로이드이슈트래커",
            description = "2022년 6월 13일 월요일 부터 7월 1일 금요일 까지",
            state = true,
            label = listOf(LabelFakeDatabase.database[1]),
            mileStone = MileStone(id = 2, title = "마스터즈 코스", deadLine = "2022-06-16"),
            createdTime = "2022-05-21",
            milestoneId = 1,
            memberId = 1
        ),
        IssueDto(
            id = 3L,
            title = "테스트",
            description = "테스트 설명",
            state = false,
            label = listOf(LabelFakeDatabase.database[2]),
            mileStone = MileStone(id = 3, title = "테스트 그룹", deadLine = "2022-06-17"),
            createdTime = "2022-05-21",
            milestoneId = 1,
            memberId = 1
        ),
        IssueDto(
            id = 4L,
            title = "안드로이드이슈트래커",
            description = "123456789",
            state = true,
            label = listOf(LabelFakeDatabase.database[3]),
            mileStone = MileStone(id = 4, title = "마스터즈 코스 숫자", deadLine = "2022-06-20"),
            createdTime = "2022-05-21",
            milestoneId = 1,
            memberId = 1
        )
    )

    private val members: List<MemberDto> = listOf(
        MemberDto(
            memberId = 1,
            memberName = "yan",
            profile = ""
        ),
        MemberDto(
            memberId = 2,
            memberName = "k",
            profile = ""
        ),
        MemberDto(
            memberId = 3,
            memberName = "yellow",
            profile = ""
        ),
        MemberDto(
            memberId = 4,
            memberName = "wooki",
            profile = ""
        ),
        MemberDto(
            memberId = 5,
            memberName = "stitch",
            profile = ""
        ),
    )

    private val milestones: List<MilestoneDto> = listOf(
        MilestoneDto(
            id = 1,
            title = "마일스톤",
            deadLine = "2022-06-14",
            description = ""
        ),
        MilestoneDto(
            id = 2,
            title = "마스터즈 코스",
            deadLine = "2022-06-16",
            description = ""
        ),
        MilestoneDto(
            id = 3,
            title = "테스트 그룹",
            deadLine = "2022-06-17",
            description = ""
        ),
        MilestoneDto(
            id = 4,
            title = "마스터즈 코스 숫자",
            deadLine = "2022-06-20",
            description = ""
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

    override suspend fun revertIssue(list: SortedMap<Int, Issue>): Result<List<IssueDto>> {
        for ((idx, issue) in list) {
            val issueDto = IssueDto(
                id = issue.id,
                title = issue.title,
                description = issue.description,
                state = issue.state,
                label = issue.label.map {
                    LabelDto(
                        id = issues.size + 1,
                        title = it.title,
                        description = it.description,
                        color = it.color,
                        textColor = it.textColor
                    )
                },
                mileStone = issue.mileStone,
                createdTime = "2022-06-21",
                milestoneId = 1,
                memberId = 1
            )
            issues.add(idx, issueDto)
        }
        return getIssue()
    }

    override suspend fun getMember(): Result<List<MemberDto>> {
        return runCatching { members }
    }

    override suspend fun getMilestone(): Result<List<MilestoneDto>> {
        return runCatching { milestones }
    }

    override suspend fun getFilterList(hm: HashMap<String, Any>): Result<List<IssueDto>> {
        return runCatching {
            var filterList: List<IssueDto> = issues
            for ((key, value) in hm) {
                when (key) {
                    "status" -> {
                        filterList = filterList.filter { it.state == value }
                    }
                    "writerId" -> {
                        filterList = filterList.filter { it.memberId == value }
                    }
                    "assignee" -> {}
                    "commentedBy" -> {}
                    "label" -> {
                        filterList = filterList.filter {
                            val find = it.label.find { label ->
                                label.id == value
                            }
                            find != null
                        }
                    }
                    "milestone" -> {
                        filterList = filterList.filter { it.mileStone.id == value }
                    }
                }
            }
            filterList
        }
    }

    override fun findByIssueName(title: String): Flow<List<IssueDto>> = flow {
        val result = issues.filter { it.title.contains(title) }
        emit(result)
    }
}