package com.example.it.issuetracker.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.BuildConfig
import com.example.it.issuetracker.data.network.GithubNetwork
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val githubNetwork = GithubNetwork.createGithubService()
    private val githubApiNetwork = GithubNetwork.createGithubApiService()

    fun getAccessToken(code: String) {
        viewModelScope.launch {

            val token = githubNetwork.getAccessToken(
                "application/json",
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET_ID,
                code
            )

            val info = githubApiNetwork.getUserInfo(
                "token ${token.accessToken}"
            )

            Log.d("LoginViewModel", "info : $info")
        }
    }
}