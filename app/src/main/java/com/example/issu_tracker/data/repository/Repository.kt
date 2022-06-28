package com.example.issu_tracker.data.repository

import android.util.Log
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.local.FriendDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val friendLocalDatabase: FriendDatabase,
    private val friendRemoteDatabase: FriendRemoteRepository
) {
    suspend fun loadFriendList(): List<User> {
        // TODO  네트워크 상태에 따른 분기 처리 필요
        Log.d("sdd","0")
        // 1. 리모트에서 로컬에 캐시해줌
        updateRemoteDatabase()


        // 2. 업데이트된 로컬을 통해서 반환해줌
        val result = friendLocalDatabase.userDao().getAll()
        return result
    }

    suspend fun updateRemoteDatabase() {
        // 1. 리모트에서 로컬에 캐시해줌
        val userList = friendRemoteDatabase.loadFriendList()
        Log.d("sdd","1")
        CoroutineScope(Dispatchers.IO).launch {
            friendLocalDatabase.userDao().deleteAllUsers()
            userList.forEach {
                launch {
                    friendLocalDatabase.userDao().insert(it)
                }
            }
        }.join()
        Log.d("sdd","2")
    }

    suspend fun loadFriendListFromRemote(): List<User> {
        return friendRemoteDatabase.loadFriendList()
    }

    suspend fun updateFriend(userList: List<User>) {
        friendRemoteDatabase.updateFriend(userList)
    }
}