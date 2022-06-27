package com.example.it.issuetracker.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.datasource.UserSharedPrefDataSource
import com.example.it.issuetracker.domain.repository.LoginRepository
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.login.LoginOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val sharedPref: UserSharedPrefDataSource,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserInfoFromPref()
    }

    private fun getUserInfoFromPref() {
        val imageUrl = sharedPref.getData(Constants.LOGIN_PREF_IMAGE_URL)
        when (sharedPref.getData(Constants.LOGIN_PREF_OPTION)) {
            LoginOption.GITHUB.option -> {
                _uiState.update { it.copy(imageUrl = imageUrl, loginOption = LoginOption.GITHUB) }
            }
            LoginOption.GOOGLE.option -> {
                _uiState.update { it.copy(imageUrl = imageUrl, loginOption = LoginOption.GOOGLE) }
            }
            else -> {
                _uiState.update { it.copy(imageUrl = imageUrl, loginOption = LoginOption.GUEST) }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = loginRepository.logout()
            if (result.isSuccess) _uiState.update { it.copy(logout = true) }
            else _uiState.update { it.copy(errorMsgId = R.string.network_error) }
        }
    }
}