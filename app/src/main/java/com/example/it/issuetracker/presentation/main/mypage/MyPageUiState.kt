package com.example.it.issuetracker.presentation.main.mypage

import com.example.it.issuetracker.presentation.login.LoginOption

data class MyPageUiState(
    val imageUrl: String = "",
    val loginOption: LoginOption = LoginOption.GUEST,
    val logout: Boolean = false,
    val errorMsgId: Int = -1,
)
