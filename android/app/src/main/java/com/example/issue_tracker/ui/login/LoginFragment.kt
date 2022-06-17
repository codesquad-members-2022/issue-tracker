package com.example.issue_tracker.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentLoginBinding
import com.example.issue_tracker.ui.IssueActivity

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
            val intent = Intent(context, IssueActivity::class.java)
            startActivity(intent)
        }
    }
}