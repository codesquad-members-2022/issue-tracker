package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Comment
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    HomeRepository {
    override suspend fun loadIssues(): List<Issue> {
        val list = mutableListOf<Issue>()
        val x = fireStore.collection("Issue").get().await()
        x.documents.forEach {
            it.documentDataToIssue()?.let { it1 -> list.add(it1) }
        }
        return list
    }
}

fun DocumentSnapshot.documentDataToIssue(): Issue? {
    try {
        val userMap = this["user"] as Map<String, String>
        val user = User(userMap["UID"] as String, userMap["name"] as String)

        val commentList = mutableListOf<Comment>()
        val comments = this["commets"] as List<Map<String, Any>>

        comments.forEach {
            val commentUser = it["commentUser"]
            commentUser as Map<String, Any>
            val user = User(commentUser["UID"] as String, commentUser["name"] as String)
            commentList.add(Comment(user, it["content"] as String, it["time"] as String))
        }

        val labelList = mutableListOf<Label>()
        val labels = this["label"] as List<Map<String, String>>
        labels.forEach { map ->
            if (map["color"] != null && map["content"] != null) {
                labelList.add(Label(map["color"]!!, map["content"]!!))
            }
        }


        return Issue(
            this["mileStone"] as String,
            labelList,
            this["title"] as String,
            this["description"] as String,
            user,
            commentList
        )
    } catch (e: Exception) {
        return null
    }

}