package com.example.issu_tracker.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.repository.Repository
import com.example.issu_tracker.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _friendList = MutableStateFlow<List<User>>(listOf())
    val friendList: StateFlow<List<User>> = _friendList

    fun loadFriendList() {
        viewModelScope.launch {
            _friendList.value = repository.loadFriendList()
        }
    }

    fun dltFriend(user: User) {
        viewModelScope.launch {
            val updateList = friendList.value.toMutableList()
            updateList.remove(user)
            repository.updateFriend(updateList)
            _friendList.value = repository.loadFriendListFromRemote()
            repository.updateRemoteDatabase()
        }
    }


}