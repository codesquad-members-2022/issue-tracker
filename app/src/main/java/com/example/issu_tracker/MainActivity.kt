package com.example.issu_tracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>
    private val githubOAuthProvider = OAuthProvider.newBuilder("github.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = Firebase.auth

        initGoogleSignInButton()
        initGitHubSignInButton()
        initSignOutButton()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = firebaseAuth.currentUser
        updateUI(currentUser)
    }

    private fun initGoogleSignInButton() {
        val gso = setGoogleSignInOptions()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignResultLauncher = registerActivityResultLauncherForGoogleSignIn()
        setGoogleSignInBtn()
    }

    private fun initGitHubSignInButton() {
        val githubBtn: AppCompatButton = findViewById(R.id.btn_github_sign_in)
        githubBtn.setOnClickListener {
            firebaseAuth.startActivityForSignInWithProvider(this, githubOAuthProvider.build())
                .addOnSuccessListener { result ->
                    result.credential?.let {
                        checkFirebaseAuth(it)
                    }
                }
                .addOnFailureListener {
                    failSignIn(it)
                }
        }
    }

    private fun setGoogleSignInBtn() {
        val button: AppCompatButton = findViewById(R.id.btn_google_sign_in)
        button.setOnClickListener {
            val singInIntent = googleSignInClient.signInIntent
            googleSignResultLauncher.launch(singInIntent)
        }
    }

    private fun registerActivityResultLauncherForGoogleSignIn() = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)

            if (task.isSuccessful) {
                val account = task.result
                account.idToken?.let {
                    checkFirebaseAuthWithGoogle(it)
                }
            } else {
                failSignIn(task.exception ?: Exception("로그인 실패"))
            }
        }
    }

    private fun setGoogleSignInOptions() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    private fun checkFirebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        checkFirebaseAuth(credential)
    }

    private fun checkFirebaseAuth(credential: AuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun failSignIn(e: Exception) {
        Toast.makeText(this, "Error : $e", Toast.LENGTH_LONG).show()
    }

    // firebase 인증 상태에 따라 UI 업데이트, 토큰 저장 및 화면 이동
    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            Log.e("Google account", "로그인 안되있음")
        } else {
            Log.e("Google account", "로그인 됨")
            // 여기서 토큰을 저장하는 프로세스 넣으면 됨
        }

    }

    private fun initSignOutButton() {
        val signOutBtn: AppCompatButton = findViewById(R.id.btn_sign_out)
        signOutBtn.setOnClickListener {
            firebaseAuth.signOut()
        }
    }

    companion object {
        private const val TAG = "LogInActivity"
    }
}