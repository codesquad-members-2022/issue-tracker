package com.example.it.issuetracker.presentation.main.issue.detail

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentDetailBinding
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.list.IssueViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : DataBindingBaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private var id: Long = -1
    private val viewModel by sharedViewModel<DetailViewModel>()
    private val issueViewModel by viewModel<IssueViewModel>()
    private val adapter = CommentAdapter { view, uid ->
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.like -> {
                    issueViewModel.addLike(id, uid)
                }
                R.id.best -> {
                    issueViewModel.addBest(id, uid)
                }
                R.id.hate -> {
                    issueViewModel.addHate(id, uid)
                }
                R.id.ok -> {
                    issueViewModel.addOk(id, uid)
                }
            }
            viewModel.getIssueDetail(id)
            true
        }
        popupMenu.inflate(R.menu.comment_menu)
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception) {

        } finally {
            popupMenu.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val bundle = arguments
        if (bundle != null) {
            id = bundle.getLong("id")
            viewModel.getIssueDetail(id)
        }
        initView()
        observerData()
    }

    override fun initView() = with(binding) {
        ivOption.setOnClickListener {
            val bottomSheet = BottomSheetMenu(
                onDeleteClick = {
                    issueViewModel.deleteIssue(id)
                    parentFragmentManager.popBackStack()
                },
                onCloseClick = {
                    issueViewModel.closeIssue(id)
                    viewModel.getIssueDetail(id)
                }
            )
            bottomSheet.show(parentFragmentManager, "detail_information")
        }
        toolbarDefaultIssue.setNavigationOnClickListener {
            val count = parentFragmentManager.backStackEntryCount
            if (count - 2 >= 0) {
                val backStackEntryAt = parentFragmentManager.getBackStackEntryAt(count - 2)
                if (backStackEntryAt.name == "search_issue") {
                    parentFragmentManager.popBackStack(
                        "search_issue",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                }
            } else {
                parentFragmentManager.popBackStack()
            }
        }
        rvComment.adapter = adapter

        viewWriter.btnOption.setOnClickListener {
            val popup = PopupMenu(requireContext(), it)
            popup.menuInflater.inflate(R.menu.detail_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        Toast.makeText(requireContext(), "수정하기 눌렀습니다.", Toast.LENGTH_SHORT).show()
                    }
                    R.id.delete -> {
                        issueViewModel.deleteIssue(id)
                        parentFragmentManager.popBackStack()
                    }
                }
                false
            }
            popup.show()
        }


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
        when (state.issue.issueStatus) {
            context?.getString(R.string.issue_open_eng) -> {
                binding.chipOpenIssue.isVisible = true
                binding.chipCloseIssue.isVisible = false
            }
            context?.getString(R.string.issue_close_eng) -> {
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