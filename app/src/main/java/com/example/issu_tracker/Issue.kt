package com.example.issu_tracker

data class Issue(
    val index: Int,
    val mileStone: String,
    val label: String,
    val title: String,
    val description: String,
)
