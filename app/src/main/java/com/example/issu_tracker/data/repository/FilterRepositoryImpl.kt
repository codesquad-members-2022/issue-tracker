package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    FilterRepository {

    override suspend fun loadUsers(): Map<String, User> {
        val userList = mutableMapOf<String, User>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_USER_PATH).get().await()

        collectionData.documents.forEach {
            val assigneeObj = it.toObject(User::class.java)
            assigneeObj?.let { user ->
                userList[user.name] = user
            }
        }
        return userList
    }

    override suspend fun loadLabel(): Map<String, Label> {
        val labelList = mutableMapOf<String, Label>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_LABEL_PATH).get().await()

        collectionData.documents.forEach { it ->
            val labelObj = it.toObject(Label::class.java)
            labelObj?.let { label ->
                labelList[label.content] = label
            }
        }
        return labelList
    }

    companion object {
        const val FIREBASE_COLLECTION_USER_PATH = "User"
        const val FIREBASE_COLLECTION_LABEL_PATH = "Label"
    }
}