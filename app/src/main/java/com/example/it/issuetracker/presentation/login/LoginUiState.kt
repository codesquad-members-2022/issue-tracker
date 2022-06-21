package com.example.it.issuetracker.presentation.login

import com.example.it.issuetracker.domain.model.LoginInformation

sealed class LoginUiState {
    object UnInitialization : LoginUiState()
    object Loading : LoginUiState()
    data class GetUserInformation(val loginInformation: LoginInformation) : LoginUiState()
}