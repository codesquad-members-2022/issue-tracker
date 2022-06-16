package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    HomeRepository {
    override suspend fun loadIssues(): List<Issue> {
        val list = mutableListOf<Issue>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_PATH).get().await()
        collectionData.documents.forEach {
            val issueObj = it.toObject(Issue::class.java)
            issueObj?.let { it1 -> list.add(it1) }
        }
        return list
    }

    companion object {
        const val FIREBASE_COLLECTION_PATH = "Issue"
    }
}
