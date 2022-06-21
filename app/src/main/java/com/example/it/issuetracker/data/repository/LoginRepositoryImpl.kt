package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.dto.toLoginInformation
import com.example.it.issuetracker.data.network.IssueTrackerService
import com.example.it.issuetracker.domain.model.LoginInformation
import com.example.it.issuetracker.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val issuerTrackerApi: IssueTrackerService,
) : LoginRepository {

    override suspend fun loginOAuthGithub(code: String): LoginInformation {
        val loginDto = issuerTrackerApi.loginOAuthGithub(code)

        return loginDto.toLoginInformation()
    }
}