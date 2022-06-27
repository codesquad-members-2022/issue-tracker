package com.example.it.issuetracker.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.datasource.UserSharedPrefDataSource
import com.example.it.issuetracker.domain.repository.LoginRepository
import com.example.it.issuetracker.presentation.common.Constants
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

    fun saveJwt(jwt: String) {
        sharedPref.saveData(Constants.LOGIN_PREF_JWT, jwt)
    }

    fun saveId(id: Long) {
        sharedPref.saveData(Constants.LOGIN_PREF_ID, id)
    }

    fun saveUserImageUrl(imageUrl: String) {
        sharedPref.saveData(Constants.LOGIN_PREF_IMAGE_URL, imageUrl)
    }

    fun saveLoginOption(loginOption: LoginOption) {
        when (loginOption) {
            LoginOption.GOOGLE -> sharedPref.saveData(Constants.LOGIN_PREF_OPTION, "GOOGLE")
            LoginOption.GITHUB -> sharedPref.saveData(Constants.LOGIN_PREF_OPTION, "GITHUB")
            LoginOption.GUEST -> sharedPref.saveData(Constants.LOGIN_PREF_OPTION, "GUEST")
        }
    }
}