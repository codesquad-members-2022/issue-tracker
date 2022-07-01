package com.example.it.issuetracker.data.datasource.local

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.datasource.LabelFakeDatabase
import com.example.it.issuetracker.data.dto.*
import com.example.it.issuetracker.presentation.main.issue.register.NewIssue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat

class IssueTrackerDefaultDataSource : IssueTrackerDataSource {

    private var issues: MutableList<IssueDto> = mutableListOf(
        IssueDto(
            id = 1L,
            title = "제목",
            description = "이슈에 대한 설명(두 줄까지 보여줄 수 있다)",
            state = true,
            labels = listOf(LabelFakeDatabase.database[0]),
            mileStone = "마일스톤",
            createdTime = "2022-05-21",
            isDelete = false
        ),
        IssueDto(
            id = 2L,
            title = "안드로이드이슈트래커",
            description = "2022년 6월 13일 월요일 부터 7월 1일 금요일 까지",
            state = true,
            labels = listOf(LabelFakeDatabase.database[1]),
            mileStone = "마스터즈 코스",
            createdTime = "2022-05-21",
            isDelete = false
        ),
        IssueDto(
            id = 3L,
            title = "테스트",
            description = "테스트 설명",
            state = false,
            labels = listOf(LabelFakeDatabase.database[2]),
            mileStone = "테스트 그룹",
            createdTime = "2022-05-21",
            isDelete = false
        ),
        IssueDto(
            id = 4L,
            title = "안드로이드이슈트래커",
            description = "123456789",
            state = true,
            labels = listOf(LabelFakeDatabase.database[3]),
            mileStone = "마스터즈 코스 숫자",
            createdTime = "2022-05-21",
            isDelete = false
        ),
        IssueDto(
            id = 5,
            title = "제목쓰",
            description = "안녕하세요. 제목쓰에 대한 설명입니다.",
            state = true,
            labels = listOf(LabelFakeDatabase.database[0], LabelFakeDatabase.database[1]),
            mileStone = "마스터즈 코스 숫자",
            createdTime = "2022-05-06 09:11:23",
            isDelete = false
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
            deadline = "2022-06-14",
            description = ""
        ),
        MilestoneDto(
            id = 2,
            title = "마스터즈 코스",
            deadline = "2022-06-16",
            description = ""
        ),
        MilestoneDto(
            id = 3,
            title = "테스트 그룹",
            deadline = "2022-06-17",
            description = ""
        ),
        MilestoneDto(
            id = 4,
            title = "마스터즈 코스 숫자",
            deadline = "2022-06-20",
            description = ""
        )
    )

    private var issueDetail = IssueDetailDto(
        id = 5,
        title = "제목쓰",
        issueStatus = true,
        writer = WriterDto(id = 1L, name = "stitch", email = "", githubId = "", imageUrl = ""),
        manager = listOf(AssigneeDto(id = 1L, githubId = "", imageUrl = "")),
        description = "안녕하세요. 제목쓰에 대한 설명입니다.",
        createdTime = "2022-05-06 09:11:23",
        labels = listOf(LabelFakeDatabase.database[0], LabelFakeDatabase.database[1]),
        milestones = MilestoneDto(id = 4,
            title = "마스터즈 코스 숫자",
            deadline = "2022-06-20",
            description = ""),
        comments = mutableListOf(
            CommentDto(
                id = 1L,
                githubId = "Daniel",
                imageUrl = "",
                content = "내용",
                createDate = "2022-05-06 12:13:13",
                like = 1,
                hate = 0,
                best = 4,
                ok = 0
            ),
            CommentDto(
                id = 2L,
                githubId = "Stitch",
                imageUrl = "",
                content = "happy day",
                createDate = "2022-05-06 12:13:13",
                like = 4,
                hate = 2,
                best = 0,
                ok = 2
            )
        )
    )

    override fun getIssue(): Flow<List<IssueDto>> = flow {
        emit(issues.filter { it.state }.toList())
    }

    override suspend fun updateIssueDelete(status: Boolean, list: List<Long>) {
        list.forEach { id ->
            val issue = issues.find { it.id == id }
            val index = issues.indexOf(issue)
            issues[index].isDelete = status
        }
    }

    override suspend fun updateIssueClose(status: Boolean, list: List<Long>) {
        list.forEach { id ->
            val issue = issues.find { it.id == id }
            val index = issues.indexOf(issue)
            issues[index].state = status
        }
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
                    "writerId" -> {}
                    "assignee" -> {}
                    "commentedBy" -> {}
                    "label" -> {
                        filterList = filterList.filter {
                            val find = it.labels.find { label ->
                                label.id == value
                            }
                            find != null
                        }
                    }
                    "milestone" -> {}
                }
            }
            filterList
        }
    }

    override suspend fun saveIssue(newIssue: NewIssue) {
        val labelList = mutableListOf<LabelDto>()
        newIssue.labels?.forEach { label ->
            labelList.add(
                LabelDto(
                    label.id,
                    label.title,
                    label.description,
                    label.color,
                    label.textColor
                )
            )
        }

        issues.add(
            IssueDto(
                issues.size + 1L,
                newIssue.title,
                newIssue.description,
                true,
                labelList,
                newIssue.milestoneId?.title ?: "",
                "2022-06-30",
            )
        )
    }

    override fun findByIssueName(title: String): Flow<List<IssueDto>> = flow {
        val result = issues.filter { it.title.contains(title) }
        emit(result)
    }

    override fun getIssueDetail(id: Long): Flow<IssueDetailDto> = flow {
        emit(issueDetail)
    }

    override suspend fun addLike(id: Long, uid: Long) {
        val map = issueDetail.comments.map {
            if (it.id == uid) {
                it.copy(like = it.like + 1)
            } else {
                it
            }
        }
        issueDetail = issueDetail.copy(comments = map)
    }

    override suspend fun addBest(id: Long, uid: Long) {
        val map = issueDetail.comments.map {
            if (it.id == uid) {
                it.copy(best = it.best + 1)
            } else {
                it
            }
        }
        issueDetail = issueDetail.copy(comments = map)
    }

    override suspend fun addHate(id: Long, uid: Long) {
        val map = issueDetail.comments.map {
            if (it.id == uid) {
                it.copy(hate = it.hate + 1)
            } else {
                it
            }
        }
        issueDetail = issueDetail.copy(comments = map)
    }

    override suspend fun addOk(id: Long, uid: Long) {
        val map = issueDetail.comments.map {
            if (it.id == uid) {
                it.copy(ok = it.ok + 1)
            } else {
                it
            }
        }
        issueDetail = issueDetail.copy(comments = map)
    }

    override suspend fun addComment(id: Long, text: String) {
        val comments = issueDetail.comments.toMutableList()
        comments.add(
            CommentDto(
                id = (issueDetail.comments.size + 1).toLong(),
                githubId = "Stitch",
                imageUrl = "",
                content = text,
                createDate = convertTimestampToDate(System.currentTimeMillis()),
                like = 0,
                best = 0,
                hate = 0,
                ok = 0
            )
        )
        issueDetail.comments = comments
    }

    private fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(timestamp)
    }
}