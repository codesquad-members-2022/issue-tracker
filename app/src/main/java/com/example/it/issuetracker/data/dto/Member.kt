package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Member

data class MemberDto(
    val memberId: Long,
    val memberName: String,
    val profile: String,
)

fun MemberDto.toMember(): Member = Member(id = memberId, name = memberName, profile = profile)