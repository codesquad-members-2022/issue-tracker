package com.example.it.issuetracker.presentation.main.issue.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.it.issuetracker.databinding.FragmentBottomSheetMenuBinding
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.list.LabelAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BottomSheetMenu : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetMenuBinding
    private val viewModel by sharedViewModel<DetailViewModel>()
    private val labelAdapter = LabelAdapter()
    private val milestoneAdapter = MilestoneAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBottomSheetMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observerData()
    }

    private fun initView() = with(binding) {
        btnClose.setOnClickListener { dismiss() }
        rvLabel.adapter = labelAdapter
        rvMilestone.adapter = milestoneAdapter
        btnIssueClose.setOnClickListener { }
        btnIssueDelete.setOnClickListener { }
    }

    private fun observerData() {
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
        labelAdapter.submitList(state.issue.labels)
        milestoneAdapter.submitList(state.issue.milestones)

    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
        binding.tvSearchResult.isVisible = false
    }
}