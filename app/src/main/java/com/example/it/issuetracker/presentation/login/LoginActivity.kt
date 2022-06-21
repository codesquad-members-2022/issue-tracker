package com.example.it.issuetracker.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.it.issuetracker.config.GITHUB_OAUTH_URL
import com.example.it.issuetracker.databinding.ActivityLoginBinding
import com.example.it.issuetracker.presentation.main.MainActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observerData()
    }

    private fun initView() = with(binding) {
        btnGithub.setOnClickListener { signInGithub() }
    }

    private fun observerData() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is LoginUiState.Loading -> {
                        handlerLoading()
                    }
                    is LoginUiState.UnInitialization -> {
                        handlerUnInitialization()
                    }
                    is LoginUiState.GetUserInformation -> {
                        handlerSuccess(state)
                    }
                }
            }
        }
    }

    private fun handlerSuccess(state: LoginUiState.GetUserInformation) {
        val loginInformation = state.loginInformation
        viewModel.saveJwt(loginInformation.jwt)
        navigateMain(loginInformation.imageUrl)
    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
        binding.btnGithub.isEnabled = false
        binding.btnGoogle.isEnabled = false
    }

    private fun handlerUnInitialization() {
        binding.progressBar.isVisible = false
        binding.btnGithub.isEnabled = true
        binding.btnGoogle.isEnabled = true
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
        code?.let { viewModel.requestLogin(it) }
    }

    private fun signInGithub() {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
        builder.setUrlBarHidingEnabled(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this@LoginActivity, Uri.parse(GITHUB_OAUTH_URL))
    }

    private fun navigateMain(imageUrl: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("imageUrl", imageUrl)
        startActivity(intent)
        finish()
    }
}