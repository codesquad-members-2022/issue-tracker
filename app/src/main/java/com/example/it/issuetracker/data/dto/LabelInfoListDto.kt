package com.example.it.issuetracker.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LabelInfoListDto(
    val labelDto: List<LabelDto>,
)
