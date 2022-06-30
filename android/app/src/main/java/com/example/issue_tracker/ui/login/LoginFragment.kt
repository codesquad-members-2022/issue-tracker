package com.example.issue_tracker.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentLoginBinding
import com.example.issue_tracker.ui.HomeActivity
import com.example.issue_tracker.ui.common.loginWithKakao
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigationControl = findNavController()
        binding.btnToSignUp.setOnClickListener {
            navigationControl.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnSingIn.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.cbKakaoLogin.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val oAuthToken = UserApiClient.loginWithKakao(requireContext())
                    Log.d("Kakao", oAuthToken.toString())
                } catch (error: Throwable) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        Log.d("Kakao", "사용자가 명시적으로 취소")
                    } else {
                        Log.e("Kakao", "인증 에러 발생", error)
                    }
                }
            }
        }
        binding.cbGithubLogin.setOnClickListener {
            navigationControl.navigate(R.id.action_loginFragment_to_gitHubWebViewFragment)
        }
    }
}