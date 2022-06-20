package com.example.it.issuetracker.presentation.main.issue

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentIssueBinding
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFragment : Fragment() {

    private lateinit var binding: FragmentIssueBinding
    private val viewModel by viewModel<IssueViewModel>()
    private val adapter = IssueAdapter { toggleMode() }
    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentIssueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        observerData()
    }

    private fun initView() = with(binding) {
        btnIssue.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_main, RegisterIssueFragment())
                .addToBackStack("register_issue")
                .commit()
        }
        rvIssue.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        rvIssue.addItemDecoration(dividerItemDecoration)
        toolbarEditIssue.setNavigationOnClickListener { toggleMode() }
        ivDelete.setOnClickListener { deleteIssues() }
        ivClose.setOnClickListener { closeIssues() }
    }

    private fun closeIssues() {
        viewModel.closeIssue()
        binding.toolbarDefaultIssue.isVisible = true
        binding.toolbarEditIssue.isVisible = false
    }

    private fun deleteIssues() {
        viewModel.deleteIssue()
        binding.toolbarDefaultIssue.isVisible = true
        binding.toolbarEditIssue.isVisible = false
    }

    private fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is IssueUiState.UnInitialization -> {
                        handlerUnInitialization()
                    }
                    is IssueUiState.Loading -> {
                        handlerLoading()
                    }
                    is IssueUiState.GetIssues -> {
                        handlerSuccess(state)
                    }
                }
            }
        }
    }

    private fun handlerUnInitialization() {
        binding.progressBar.isVisible = false
    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
    }

    private fun handlerSuccess(state: IssueUiState.GetIssues) {
        binding.progressBar.isVisible = false
        adapter.submitList(state.issues)
    }

    private fun toggleMode() {
        if (!binding.toolbarDefaultIssue.isVisible) defaultMode() else editMode()
    }

    private fun editMode() {
        binding.toolbarDefaultIssue.isVisible = false
        binding.toolbarEditIssue.isVisible = true
        viewModel.toggleViewType()
    }

    private fun defaultMode() {
        binding.toolbarDefaultIssue.isVisible = true
        binding.toolbarEditIssue.isVisible = false
        viewModel.toggleViewType()
    }

    override fun onPause() {
        super.onPause()
        binding.toolbarDefaultIssue.isVisible = true
        binding.toolbarEditIssue.isVisible = false
        viewModel.updateDefaultViewType()
    }
}