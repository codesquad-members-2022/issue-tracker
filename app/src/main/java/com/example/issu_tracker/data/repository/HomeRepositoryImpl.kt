package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.IssueDto
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.toIssue
import com.example.issu_tracker.ui.home.userUid
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    HomeRepository {
    override suspend fun loadIssues(): List<Issue> {
        val list = mutableListOf<Issue>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).get().await()

        collectionData.documents.forEach {
            val issueObj = it.toObject(IssueDto::class.java)
            issueObj?.id = it.id
            issueObj?.let { it1 ->
                it1.toIssue()?.let { it2 -> list.add(it2) }
                // 데이터를 추가하는 코드
                // fireStore.collection(FIREBASE_COLLECTION_PATH).document().set(it1)
            }
        }
        return list
    }

    override suspend fun updateIssueState(itemId: String, boolean: Boolean) {

        fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(itemId)
            .update("state", false).await()

    }


    override suspend fun deleteIssueList(list: List<Issue>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in list) {
                fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(i.id).delete()
            }
        }.join()
    }


    override suspend fun updateIssueListState(list: List<Issue>, boolean: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in list) {
                fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(i.id)
                    .update("state", false)
            }
        }.join()
    }

    override suspend fun loadFriendList(): List<User> {
        val userData =
            fireStore.collection(FIREBASE_COLLECTION_FRIEND_PATH).document("User1").get().await()
        val friendData = userData["friend"] as List<Map<String, String>>
        Log.d("friend", friendData.toString())

        val friendList = mutableListOf<User>()

        friendData.forEach {
            val user =
                User(it["UID"] ?: "", it["name"] ?: "",
                    it["userPhoto"]?.let { it1 -> convertUriToImageUrl(it1) })
            friendList.add(user)
        }
        return friendList
    }

    private fun convertUriToImageUrl(uri: String): String {
        val parsedUri = StringBuilder(FIRE_STORAGE_FIRST_PATH)
        val splitUri = uri.substring(5, uri.length).split("/")
        parsedUri.append(splitUri[0])
        parsedUri.append(FIRE_STORAGE_MIDDLE_PATH)
        parsedUri.append(splitUri[1])
        parsedUri.append(FIRE_STORAGE_LAST_PATH)
        return parsedUri.toString()
    }


    companion object {
        private const val FIRE_STORAGE_FIRST_PATH = "https://firebasestorage.googleapis.com/v0/b/"
        private const val FIRE_STORAGE_MIDDLE_PATH = "/o/"
        private const val FIRE_STORAGE_LAST_PATH = "?alt=media"
        const val FIREBASE_COLLECTION_ISSUE_PATH = "Issue"
        const val FIREBASE_COLLECTION_FRIEND_PATH = "User"
    }
}
