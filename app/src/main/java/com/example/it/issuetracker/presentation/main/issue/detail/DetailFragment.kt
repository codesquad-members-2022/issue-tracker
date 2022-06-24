package com.example.it.issuetracker.presentation.main.issue.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentDetailBinding
import com.example.it.issuetracker.presentation.common.BaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val viewModel by sharedViewModel<DetailViewModel>()
    private val adapter = CommentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val bundle = arguments
        if (bundle != null) {
            viewModel.getIssueDetail(bundle.getLong("id"))
        }
        initView()
        observerData()
    }

    override fun initView() = with(binding) {
        ivOption.setOnClickListener {
            val bottomSheet = BottomSheetMenu()
            val bundle = Bundle()
            bottomSheet.show(parentFragmentManager, "detail_information")

        }
        toolbarDefaultIssue.setNavigationOnClickListener {
            parentFragmentManager.popBackStack(
                "search_issue",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        rvComment.adapter = adapter
    }

    override fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.issueDetail.collectLatest { state ->
                when (state) {
                    is DetailUiState.UnInitialization -> {
                        handlerUnInitialization()
                    }
                    is DetailUiState.Loading -> {
                        handlerLoading()
                    }
                    is DetailUiState.GetDetailIssue -> {
                        handlerSuccess(state)
                    }
                }
            }
        }
    }

    private fun handlerUnInitialization() {
        binding.progressBar.isVisible = false
    }

    private fun handlerSuccess(state: DetailUiState.GetDetailIssue) {
        binding.progressBar.isVisible = false
        binding.tvSearchResult.isVisible = false
        binding.issue = state.issue
        adapter.submitList(state.issue.comments)
        Log.d("test", "handlerSuccess 1 ${state.issue.issueStatus}")
        when (state.issue.issueStatus) {
            context?.getString(R.string.issue_open_eng) -> {
                Log.d("test", "handlerSuccess 2")
                binding.chipOpenIssue.isVisible = true
                binding.chipCloseIssue.isVisible = false
            }
            context?.getString(R.string.issue_close_eng) -> {
                Log.d("test", "handlerSuccess 3")
                binding.chipOpenIssue.isVisible = false
                binding.chipCloseIssue.isVisible = true
            }
        }
    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
        binding.tvSearchResult.isVisible = false
    }

}