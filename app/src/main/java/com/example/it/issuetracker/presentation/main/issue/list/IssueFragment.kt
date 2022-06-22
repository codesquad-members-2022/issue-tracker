package com.example.it.issuetracker.presentation.main.issue.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentIssueBinding
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.presentation.common.BaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.customview.CustomSnackBar
import com.example.it.issuetracker.presentation.main.issue.search.SearchFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFragment : BaseFragment<FragmentIssueBinding>(R.layout.fragment_issue) {

    private val viewModel by viewModel<IssueViewModel>()
    private val adapter = IssueAdapter { toggleMode() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val filterList = bundle.getSerializable("filter") as List<Issue>
            viewModel.getFilterList(filterList)
        }

        initView()
        observerData()
    }

    override fun initView() = with(binding) {
        ivSearch.setOnClickListener { navigateFragment(SearchFragment(), "search_issue") }
        btnIssue.setOnClickListener { navigateFragment(SearchFragment(), "register_issue") }
        rvIssue.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        rvIssue.addItemDecoration(dividerItemDecoration)
        toolbarEditIssue.setNavigationOnClickListener { toggleMode() }
        ivDelete.setOnClickListener { deleteIssues() }
        ivClose.setOnClickListener { closeIssues() }
        toolbarDefaultIssue.setNavigationOnClickListener {
            navigateFragment(
                SearchFragment(),
                "filter"
            )
        }
    }

    private fun navigateFragment(fragment: Fragment, name: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .addToBackStack(name)
            .commit()
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

    override fun observerData() {
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
                    is IssueUiState.NotFound -> {
                        handlerNotFound()
                    }
                }
            }
        }

        repeatOnLifecycleExtension {
            viewModel.cache.collectLatest {
                if (it.message != "") {
                    CustomSnackBar.make(binding.issueContainer, "선택한 이슈를 닫았습니다.") {
                        viewModel.revertIssue(it.issues)
                    }.show()
                }
            }
        }
    }

    private fun handlerNotFound() {
        binding.progressBar.isVisible = false
        binding.tvSearchResult.isVisible = true
    }

    private fun handlerUnInitialization() {
        binding.progressBar.isVisible = false
    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
    }

    private fun handlerSuccess(state: IssueUiState.GetIssues) {
        binding.progressBar.isVisible = false
        binding.tvSearchResult.isVisible = false
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