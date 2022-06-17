package com.example.it.issuetracker.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.datasource.SharedPrefDataSource
import com.example.it.issuetracker.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val sharedPref: SharedPrefDataSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    fun requestLogin(code: String) = viewModelScope.launch {
        _uiState.value = LoginUiState.Loading
        val loginInformation = loginRepository.loginOAuthGithub(code)
        _uiState.value = LoginUiState.GetUserInformation(loginInformation = loginInformation)
    }

    fun saveJwt(jwt: String) {
        sharedPref.saveData("jwt", jwt)
    }

}