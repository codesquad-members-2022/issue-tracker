package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) : FilterRepository {

    override suspend fun loadUsers(): List<String> {
        val list = mutableListOf<String>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_USER_PATH).get().await()

        collectionData.documents.forEach {
            val issueObj = it.toObject(User::class.java)
            issueObj?.let { label ->
                list.add(label.name)
            }
        }
        return list
    }

    override suspend fun loadLabel(): List<String> {
        val list = mutableListOf<String>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_LABEL_PATH).get().await()

        collectionData.documents.forEach {
            val issueObj = it.toObject(Label::class.java)
            issueObj?.let { label ->
                list.add(label.content)
            }
        }
        return list
    }

    companion object {
        const val FIREBASE_COLLECTION_USER_PATH = "User"
        const val FIREBASE_COLLECTION_LABEL_PATH = "Label"
    }
}