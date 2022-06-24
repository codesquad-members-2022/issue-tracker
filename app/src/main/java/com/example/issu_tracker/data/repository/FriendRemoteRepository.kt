package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.User
import kotlinx.coroutines.tasks.await

interface FriendRemoteRepository {

    suspend fun loadFriendList(): List<User>
    suspend fun updateFriend(users: List<User>)

}