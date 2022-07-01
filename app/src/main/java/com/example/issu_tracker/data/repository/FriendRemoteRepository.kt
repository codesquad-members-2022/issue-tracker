package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.network.NetworkResult
import kotlinx.coroutines.tasks.await

interface FriendRemoteRepository {

    suspend fun loadFriendList(): NetworkResult<List<User>>
    suspend fun updateFriend(users: List<User>)

}