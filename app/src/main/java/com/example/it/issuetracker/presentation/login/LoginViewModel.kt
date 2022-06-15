package com.example.it.issuetracker.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.repository.LoginRepositoryImpl
import com.example.it.issuetracker.domain.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.lang.NullPointerException

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepositoryImpl(),
) : ViewModel() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isUserRegistered = MutableStateFlow<Boolean>(false)
    val isUserRegistered: MutableStateFlow<Boolean> = _isUserRegistered

    fun firebaseAuthWithGithub(task: () -> Task<AuthResult>) = viewModelScope.launch(Dispatchers.IO) {
        val result = task().await()
            try {
                val credential = result.credential
                if (credential != null) {
                    val uid = loginRepository.signInWithCredential(auth, credential).getOrThrow()
                    val isRegistered = loginRepository.checkUserRegistered(uid).getOrThrow()
                    if (!isRegistered) registerUser()
                    _isUserRegistered.value = isRegistered
                }
            } catch (e: FirebaseNetworkException) {
            } catch (e: NullPointerException) {
            } catch (e: Exception) {
            }
        }

    fun firebaseAuthWithGoogle(idToken: String?) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (idToken != null) {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val uid = loginRepository.signInWithCredential(auth, credential).getOrThrow()
                val isRegistered = loginRepository.checkUserRegistered(uid).getOrThrow()
                if (!isRegistered) registerUser()
                _isUserRegistered.value = isRegistered
            }
        } catch (e: FirebaseNetworkException) {
        } catch (e: NullPointerException) {
        } catch (e: Exception) {
        }
    }

    private fun registerUser() = viewModelScope.launch(Dispatchers.IO) {
        val userList = loginRepository.getUserUidList().getOrThrow()
        Log.d("test", "registerUser: $userList")
        loginRepository.registerUser(userList).getOrThrow()
    }
}