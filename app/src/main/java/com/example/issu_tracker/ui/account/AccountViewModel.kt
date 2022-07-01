package com.example.issu_tracker.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.repository.Repository
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _friendList = MutableStateFlow<NetworkResult<List<User>>>(NetworkResult.Loading())
    val friendList: StateFlow<NetworkResult<List<User>>> = _friendList


    fun loadFriendList() {
        viewModelScope.launch {
            val result = repository.loadFriendList()
            _friendList.value = result
        }
    }

    fun dltFriend(user: User) {
        viewModelScope.launch {
            if (friendList.value is NetworkResult.Success) {
                val updateList = (friendList.value as NetworkResult.Success).data.toMutableList()
                updateList.remove(user)
                repository.updateFriend(updateList)
                loadFriendList()
                repository.updateLocalDatabase()
            }
        }
    }


}