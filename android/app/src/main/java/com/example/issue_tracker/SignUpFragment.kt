package com.example.issue_tracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.issue_tracker.databinding.FragmentSignUpBinding
import java.util.regex.Pattern


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private var idFlag = false
    private var passwordFlag = false
    private var passwordCheckFlag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.isEnabled = false

        binding.idTextInputLayout.editText?.addTextChangedListener(idListener)
        binding.idTextInputEditText.hint = resources.getString(R.string.id_hint)
        binding.idTextInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.idTextInputEditText.hint = ""
            } else {
                binding.idTextInputEditText.hint = resources.getString(R.string.id_hint)
            }
        }

        binding.passwordTextInputLayout.editText?.addTextChangedListener(passwordListener)

        binding.passwordRecheckTextInputLayout.editText?.addTextChangedListener(
            passwordRecheckListener
        )
    }

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.idTextInputLayout.error = "아이디를 입력해주세요."
                        idFlag = false
                    }
                    !checkEmailRegex(s.toString()) -> {
                        binding.idTextInputLayout.error = "이메일 아이디 양식이 맞지 않습니다"
                        idFlag = false
                    }
                    else -> {
                        binding.idTextInputLayout.error = null
                        idFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    private val passwordListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.passwordTextInputLayout.error = "비밀번호를 입력해주세요."
                        passwordFlag = false
                    }
                    s.isNotEmpty() -> {
                        binding.passwordTextInputLayout.error = null
                        passwordFlag = true
                        when {
                            binding.passwordRecheckTextInputLayout.editText?.text.toString() != ""
                                    && binding.passwordRecheckTextInputLayout.editText?.text.toString() != binding.passwordTextInputLayout.editText?.text.toString() -> {
                                binding.passwordRecheckTextInputLayout.error = "비밀번호가 일치하지 않습니다"
                                passwordCheckFlag = false
                                passwordFlag = true
                            }
                            else -> {
                                binding.passwordRecheckTextInputLayout.error = null
                                passwordCheckFlag = true
                            }
                        }
                    }
                }
                flagCheck()
            }
        }
    }

    private val passwordRecheckListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        passwordCheckFlag = false
                    }
                    binding.passwordRecheckTextInputLayout.editText?.text.toString() != binding.passwordTextInputLayout.editText?.text.toString() -> {
                        binding.passwordRecheckTextInputLayout.error = "비밀번호가 일치하지 않습니다"
                        passwordCheckFlag = false
                    }
                    else -> {
                        binding.passwordRecheckTextInputLayout.error = null
                        passwordCheckFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    // 이메일 정규식 확인
    private fun checkEmailRegex(id: String): Boolean {
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, id)
    }

    fun flagCheck() {
        binding.nextButton.isEnabled = idFlag && passwordFlag && passwordCheckFlag
    }
}