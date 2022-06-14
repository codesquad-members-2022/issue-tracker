package com.example.it.issuetracker.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.config.GITHUB_CLIENT_ID
import com.example.it.issuetracker.config.GITHUB_SECRET_ID
import com.example.it.issuetracker.data.network.GithubNetwork
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val githubNetwork = GithubNetwork.createGithubService()
    private val githubApiNetwork = GithubNetwork.createGithubApiService()

    fun getAccessToken(code: String) {
        viewModelScope.launch {
            val token = githubNetwork.getAccessToken("application/json",
                GITHUB_CLIENT_ID,
                GITHUB_SECRET_ID,
                code)

            val info = githubApiNetwork.getUserInfo("token ${token.accessToken}")
            Log.d(TAG, "getAccessToken: ${info.toString()}")
        }
    }

    companion object {
        const val TAG = "test"
    }
}