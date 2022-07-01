package com.example.it.issuetracker.presentation.main.issue.register

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentRegisterIssueBinding
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import io.noties.markwon.Markwon

class RegisterIssueFragment :
    DataBindingBaseFragment<FragmentRegisterIssueBinding>(R.layout.fragment_register_issue) {

    private val imm: InputMethodManager by lazy { activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private lateinit var markOne: Markwon
    private var markDownFlag = false
    private var previousText = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        markOne = Markwon.builder(requireContext())
            .build()
        initView()
    }

    override fun initView() {
        previewMarkDown()
        binding.etText.text.toString()
    }

    private fun previewMarkDown() {
        binding.btn.setOnClickListener {
            if (!markDownFlag) getMarkDownView()
            else getEditView()
        }
    }

    private fun getMarkDownView() {
        previousText = binding.etText.text.toString()
        binding.etText.isEnabled = false
        imm.hideSoftInputFromWindow(binding.etText.windowToken, 0)
        markOne.setMarkdown(binding.etText, binding.etText.text.toString())
        markDownFlag = true
    }

    private fun getEditView() {
        with(binding) {
            etText.setText(previousText)
            etText.isEnabled = true
            etText.setSelection(etText.text.toString().length)
        }
        imm.showSoftInput(binding.etText, 0)
        previousText = ""
        markDownFlag = false
    }

    override fun observerData() = Unit
}