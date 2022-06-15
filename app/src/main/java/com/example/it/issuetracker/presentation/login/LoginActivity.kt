package com.example.it.issuetracker.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.ActivityLoginBinding
import com.example.it.issuetracker.presentation.issue.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GithubAuthProvider
import com.google.firebase.auth.OAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()
    private val signLauncher =
        registerForActivityResult(SignInIntentContract()) { tokenId: String? ->
            tokenId?.let { viewModel.firebaseAuthWithGoogle(it) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        autoLogin()
        initView()
        observerData()
    }

    private fun initView() {
        with(binding) {
            btnGithub.setOnClickListener { signInGithub() }
            btnGoogle.setOnClickListener { signInGoogle() }
        }
    }

    private fun observerData() {
        lifecycleScope.launch {
            viewModel.isUserRegistered.collect {
                if (it) {
                    navigateIssueActivity()
                }
            }
        }
    }

    private fun autoLogin() {
        if (viewModel.auth.currentUser == null) return
        signInGoogle()
    }

    private fun signInGoogle() {
        signLauncher.launch(getString(R.string.default_web_client_id))
    }

    private fun signInGithub() {
        viewModel.firebaseAuthWithGithub {
            val provider = OAuthProvider.newBuilder("github.com")
            viewModel.auth.startActivityForSignInWithProvider(this, provider.build())
        }
    }

    private fun navigateIssueActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}