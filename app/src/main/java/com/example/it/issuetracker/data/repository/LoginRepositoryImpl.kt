package com.example.it.issuetracker.data.repository

import android.util.Log
import com.example.it.issuetracker.domain.repository.LoginRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
) : LoginRepository {
    override suspend fun signInWithCredential(
        auth: FirebaseAuth,
        credential: AuthCredential,
    ): Result<String> {
        return runCatching {
            val authResult = auth.signInWithCredential(credential).await()
            authResult.user?.uid ?: throw NullPointerException("Error")
        }
    }

    override suspend fun checkUserRegistered(userUid: String): Result<Boolean> {
        return runCatching {
            val documentReference = db.collection("user").document(userUid)
            val snapshot = documentReference.get().await()
            snapshot != null
        }
    }

    override suspend fun getUserUidList(): Result<List<String>> {
        return runCatching {
            val querySnapshot = db.collection("users").get().await()
            val snapshots = querySnapshot.documents
            snapshots.map { it.id }
        }
    }

    override suspend fun registerUser(userList: List<String>): Result<Boolean> {
        return runCatching {
            val currentUid = firebaseAuth.currentUser?.uid ?: throw NullPointerException("Null Pointer Error")
            if (userList.contains(currentUid)) {
                throw Exception("Duplicated uid Error")
            }
            val data = hashMapOf("uid" to currentUid)
            db.collection("users").document(currentUid).set(data)
            true
        }
    }
}