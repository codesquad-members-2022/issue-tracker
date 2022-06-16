package com.example.issu_tracker.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

//data class Issue(
//    val index: Int,
//    val mileStone: String,
//    val label: String,
//    val title: String,
//    val description: String,
//)
data class Issue(
    val mileStone: String,
    val label: List<String>,
    val title: String,
    val description: String,
    val user: User,
    val comments: List<Comment>
)
