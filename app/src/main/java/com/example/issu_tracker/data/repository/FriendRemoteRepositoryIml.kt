package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.User
import com.example.issu_tracker.ui.home.userUid
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.StringBuilder
import javax.inject.Inject


class FriendRemoteRepositoryIml @Inject constructor(private val fireStore: FirebaseFirestore) :
    FriendRemoteRepository {

    override suspend fun loadFriendList(): List<User> {
        val userData =
            fireStore.collection(HomeRepositoryImpl.FIREBASE_COLLECTION_FRIEND_PATH)
                .document(userUid).get().await()
        val friendData = userData["friend"] as List<Map<String, String>>
        Log.d("friend", friendData.toString())

        val friendList = mutableListOf<User>()

        friendData.forEach {
            val user =
                User(
                    it["UID"] ?: "", it["name"] ?: "",
                    it["userPhoto"] ?: ""
                )
            friendList.add(user)
        }
        return friendList
    }

    override suspend fun updateFriend(users: List<User>) {
        fireStore.collection(HomeRepositoryImpl.FIREBASE_COLLECTION_FRIEND_PATH)
            .document(userUid).update("friend", users)
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
    }
}