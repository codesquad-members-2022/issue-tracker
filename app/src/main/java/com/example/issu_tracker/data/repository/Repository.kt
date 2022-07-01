package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.local.IssueTrackerDatabase

import com.example.issu_tracker.data.network.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val friendLocalDatabase: IssueTrackerDatabase,
    private val friendRemoteDatabase: FriendRemoteRepository
) {
    suspend fun loadFriendList(): NetworkResult<List<User>> {
        // 1. 네트워크 O -> 리모트에서 로컬에 캐시해줌, 로컬에서 내려주기
        // 2. 네트워크 X -> 로컬에서 내려주기

        val loadResult = friendRemoteDatabase.loadFriendList()

        return if (loadResult is NetworkResult.Success) {
            Log.d(":sdfsd", loadResult.data[0].toString())
            updateLocalDatabase(loadResult.data)
            Log.d(":sdfsd", (friendLocalDatabase.userDao().getAll()).size.toString())
            NetworkResult.Success(friendLocalDatabase.userDao().getAll())

        } else {
            Log.d(":sdfsd", (friendLocalDatabase.userDao().getAll()).size.toString())
            NetworkResult.Cached(friendLocalDatabase.userDao().getAll())
        }


    }

    suspend fun updateLocalDatabase(userList: List<User> = listOf()) {
        // 1. 리모트에서 로컬에 캐시해줌
        coroutineScope() {
            launch {
                friendLocalDatabase.userDao().deleteAllUsers()
                userList.forEach {
                    launch {
                        friendLocalDatabase.userDao().insert(it)
                    }
                }
            }
        }
    }

    suspend fun updateFriend(userList: List<User>) {
        friendRemoteDatabase.updateFriend(userList)
    }

}