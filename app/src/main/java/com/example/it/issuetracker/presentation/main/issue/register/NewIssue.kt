package com.example.it.issuetracker.presentation.main.issue.register

import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone

data class NewIssue(
    val title: String,
    val description: String,
    // TODO: 서버의 레이블 추가/수정 기능이 완료되면 인덱스 번호로 변경해야 함
    val labels: List<Label>? = null,
    val milestoneId: MileStone? = null,
    val assignee: Long? = null
)
