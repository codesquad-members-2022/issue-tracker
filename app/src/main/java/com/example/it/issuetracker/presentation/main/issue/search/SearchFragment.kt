package com.example.it.issuetracker.presentation.main.issue.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentSearchBinding
import com.example.it.issuetracker.presentation.common.BaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.list.IssueUiState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = SearchIssueAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observerData()
    }

    override fun initView(): Unit = with(binding) {
        btnExit.setOnClickListener { parentFragmentManager.popBackStack() }
        rvIssue.adapter = adapter
        etSearch.doAfterTextChanged {
            viewModel.findIssue(it.toString())
        }
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
}