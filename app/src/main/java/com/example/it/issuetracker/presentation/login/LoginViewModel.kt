package com.example.it.issuetracker.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _token = MutableStateFlow<String>("")
    val token = _token.asStateFlow()

    fun getAccessToken(code: String) = viewModelScope.launch {
        val accessToken = loginRepository.getAccessToken(code)
        _token.value = accessToken
        Log.d("test", "getAccessToken: $accessToken")
    }

    companion object {
        const val TAG = "test"
    }
}