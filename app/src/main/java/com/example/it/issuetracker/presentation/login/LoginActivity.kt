package com.example.it.issuetracker.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.it.issuetracker.config.GITHUB_OAUTH_URL
import com.example.it.issuetracker.databinding.ActivityLoginBinding
import com.example.it.issuetracker.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        btnGithub.setOnClickListener { signInGithub() }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
        code?.let {
            viewModel.getAccessToken(it)
            navigateMain()
        }
    }

    private fun signInGithub() {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
        builder.setUrlBarHidingEnabled(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this@LoginActivity, Uri.parse(GITHUB_OAUTH_URL))
    }

    private fun navigateMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}