package com.example.it.issuetracker.presentation.main.mypage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMyPageBinding
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.login.LoginActivity
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : DataBindingBaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel: MyPageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        observerData()
    }

    override fun initView() {
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(R.string.logout_question_title_string)
                setMessage(R.string.logout_question_message_string)
                setPositiveButton(R.string.logout_question_positive_string) { _, _ ->
                    viewModel.logout()
                }
                setNegativeButton(R.string.logout_question_negative_string, null)
                show()
            }
        }
    }

    override fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.uiState.collect { state ->
                setUserImage(state.imageUrl)
                binding.tvLoginOption.text =
                    getString(R.string.user_login_option_string, state.loginOption.option)
            }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.logout }
                .distinctUntilChanged()
                .collect { logout ->
                    if (logout) goToLoginActivity()
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.errorMsgId }
                .distinctUntilChanged()
                .collect { msgId ->
                    if (msgId == Constants.INIT_ERROR_MSG_ID) return@collect
                    val msg = getString(msgId)
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun goToLoginActivity() {
        val loginIntent = Intent(activity, LoginActivity::class.java)
        startActivity(loginIntent)
        requireActivity().finish()
    }

    private fun setUserImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .error(R.drawable.ic_my_page)
            .into(binding.userImage)
    }
}