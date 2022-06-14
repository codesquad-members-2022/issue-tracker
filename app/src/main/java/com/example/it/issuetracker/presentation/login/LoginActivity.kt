package com.example.it.issuetracker.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.it.issuetracker.R
import com.example.it.issuetracker.config.GITHUB_OAUTH_URL
import com.example.it.issuetracker.databinding.ActivityLoginBinding
import com.example.it.issuetracker.presentation.issue.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var gsc: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions
    private lateinit var auth: FirebaseAuth
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                runCatching {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWIthGoogle(account)
                }.onFailure {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null) { navigateIssueActivity() }
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)
        initView()
    }

    private fun initView() {
        with(binding) {
            btnGithub.setOnClickListener { signInGithub() }
            btnGoogle.setOnClickListener { sigInGoogle() }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
        code?.let {
            val viewModel = LoginViewModel()
            viewModel.getAccessToken(it)
            navigateIssueActivity()
        }
    }

    private fun sigInGoogle() {
        val signIn = gsc.signInIntent
        resultLauncher.launch(signIn)
    }

    private fun signInGithub() {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
        builder.setUrlBarHidingEnabled(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this@LoginActivity, Uri.parse(GITHUB_OAUTH_URL))
    }

    private fun navigateIssueActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun firebaseAuthWIthGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    navigateIssueActivity()
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                }
            })
    }
}