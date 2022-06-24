package com.example.it.issuetracker.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.datasource.UserSharedPrefDataSource
import com.example.it.issuetracker.domain.model.LoginInformation
import com.example.it.issuetracker.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val sharedPref: UserSharedPrefDataSource,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    fun requestLogin(code: String) = viewModelScope.launch {
        _uiState.value = LoginUiState.Loading
        val loginInformation = loginRepository.loginOAuthGithub(code)
        _uiState.value = LoginUiState.GetUserInformation(loginInformation = loginInformation)
    }

    private fun saveJwt(jwt: String) {
        sharedPref.saveData("jwt", jwt)
    }

    private fun saveId(id: Long) {
        sharedPref.saveData("id", id)
    }

    private fun saveProfile(imageUrl: String) {
        sharedPref.saveData("profile", imageUrl)
    }

    fun saveUser(loginInformation: LoginInformation) {
        saveJwt(loginInformation.jwt)
        saveId(loginInformation.id)
        saveProfile(loginInformation.imageUrl)
    }

}