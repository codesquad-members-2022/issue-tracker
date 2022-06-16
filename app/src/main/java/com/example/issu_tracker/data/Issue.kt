package com.example.issu_tracker.data

//data class Issue(
//    val index: Int,
//    val mileStone: String,
//    val label: String,
//    val title: String,
//    val description: String,
//)
data class Issue(
    val mileStone: String,
    val labels: List<Label>,
    val title: String,
    val description: String,
    val user: User,
    val comments: List<Comment>
)

data class Label(val color: String, val content: String)