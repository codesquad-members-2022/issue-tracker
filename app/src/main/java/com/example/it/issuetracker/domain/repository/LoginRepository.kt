package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.domain.model.LoginInformation

interface LoginRepository {

    suspend fun loginOAuthGithub(code: String): LoginInformation
}