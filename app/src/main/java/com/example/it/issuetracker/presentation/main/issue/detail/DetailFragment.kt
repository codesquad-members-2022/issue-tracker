package com.example.it.issuetracker.presentation.main.issue.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentDetailBinding
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.list.IssueViewModel
import com.example.it.issuetracker.presentation.main.issue.register.RegisterIssueFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : DataBindingBaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private var id: Long = -1
    private val imm: InputMethodManager by lazy { activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private val viewModel by sharedViewModel<DetailViewModel>()
    private val issueViewModel by viewModel<IssueViewModel>()
    private val adapter = CommentAdapter { view, uid -> commenterOptionMenuEvent(view, uid) }

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

    override fun initView() {
        val dividerItemDecoration =
            DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        binding.rvComment.addItemDecoration(dividerItemDecoration)
        binding.rvComment.adapter = adapter
        binding.btnUp.setOnClickListener { viewModel.upPosition() }
        binding.btnDown.setOnClickListener { viewModel.downPosition() }
        detailOptionMenuEvent()
        toolbarBackButtonEvent()
        writerOptionMenuEvent()
        addCommentEvent()
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

        repeatOnLifecycleExtension {
            viewModel.currentPosition.collectLatest { position ->
                adapter.setCurrentPosition(position)
                smoothPosition(position)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.focusPosition.collectLatest { position ->
                smoothPosition(position)
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
            true -> {
                binding.chipOpenIssue.isVisible = true
                binding.chipCloseIssue.isVisible = false
            }
            false -> {
                binding.chipOpenIssue.isVisible = false
                binding.chipCloseIssue.isVisible = true
            }
        }
    }

    private fun handlerLoading() {
        binding.progressBar.isVisible = true
        binding.tvSearchResult.isVisible = false
    }

    private fun addCommentEvent() {
        binding.btnAddComment.setOnClickListener {
            viewModel.addComment(id, binding.etComment.text.toString())
            imm.hideSoftInputFromWindow(binding.etComment.windowToken, 0)
            binding.etComment.text.clear()
        }
    }

    private fun writerOptionMenuEvent() {
        binding.viewWriter.btnOption.setOnClickListener {
            val popup = PopupMenu(requireContext(), it)
            popup.menuInflater.inflate(R.menu.detail_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        navigateRegisterPage()
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

    private fun toolbarBackButtonEvent() {
        binding.toolbarDefaultIssue.setNavigationOnClickListener {
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
    }

    private fun detailOptionMenuEvent() {
        binding.ivOption.setOnClickListener {
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
    }

    private fun commenterOptionMenuEvent(view: View, uid: Long) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.like -> viewModel.addLike(id, uid)
                R.id.best -> viewModel.addBest(id, uid)
                R.id.hate -> viewModel.addHate(id, uid)
                R.id.ok -> viewModel.addOk(id, uid)
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

    private fun smoothPosition(position: Int) {
        lifecycleScope.launch {
            delay(200L)
            binding.rvComment.scrollToPosition(position)
        }
    }

    private fun navigateRegisterPage() {
        val registerIssueFragment = RegisterIssueFragment().apply {
            setOnRefreshListener { issueViewModel.getIssues() }
        }
        registerIssueFragment.arguments = bundleOf(Constants.ISSUE_BUNDLE_KEY to binding.issue)
        parentFragmentManager.commit {
            addToBackStack("register_issue")
            replace(R.id.container_main, registerIssueFragment)
        }
    }
}