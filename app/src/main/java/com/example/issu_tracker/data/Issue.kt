package com.example.issu_tracker.data

import com.google.gson.annotations.SerializedName

data class Label(val color: String = "", val content: String = "")

data class Issue(
    @SerializedName("commets")
    val comments: List<Comment> = listOf(),
    val description: String = "",
    val label: List<Label> = listOf(),
    val mileStone: String = "",
    val state: Boolean = true,
    val title: String = "",
    val user: User? = null
)