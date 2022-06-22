package com.example.issu_tracker.ui.issue

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.ui.common.SwipeHelperCallback
import com.example.issu_tracker.data.FilterCondition
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.FragmentIssueBinding
import com.example.issu_tracker.ui.DetailIssueActivity
import com.example.issu_tracker.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IssueFragment : Fragment() {
    private lateinit var binding: FragmentIssueBinding
    private lateinit var issueAdapter: IssueAdapter
    private lateinit var navController: NavController
    private val issueViewModel: IssueViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)

        saveFilterConditionFromFilterFragment()
        settingRecyclerview()
        updateRecyclerview()
        navigateFilterScreen()
        navigateIssueEditor()
        listenEditModeEvent()
        setSelectedIssueCount()
        
        return binding.root
    }

    private fun saveFilterConditionFromFilterFragment() {
        val conditions = arguments?.getParcelable<FilterCondition>("filterCondition")
        conditions?.let {
            homeViewModel.filterIssueList(conditions)
        }   
    }

    private fun setSelectedIssueCount() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                issueViewModel.selectedIssueList.collect {
                    binding.tvIssueSelectedCount.text = it.size.toString()
                }
            }
        }
    }

    private fun listenEditModeEvent() {

        // editMode 나가기
        binding.ibEditClose.setOnClickListener {
            issueAdapter.isEditMode = false
            binding.clIssueOriginalModeTop.visibility = View.VISIBLE
            binding.clIssueEditModeTop.visibility = View.GONE
            issueAdapter.notifyDataSetChanged()
            issueViewModel.clearSelectedList()
        }

        // 선택요소 수정
        binding.ibIssueClose.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.updateIssueList(issueViewModel.selectedIssueList.value, false)
                    issueViewModel.clearSelectedList()
                }
            }
        }

        // 선택요소 삭제
        binding.ibIssueDelete.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    homeViewModel.deleteIssueList(issueViewModel.selectedIssueList.value)
                    issueViewModel.clearSelectedList()
                }
            }
        }

    }

    private fun updateRecyclerview() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // open 상태만 보여주기
                    homeViewModel.issueList.collect {
                        issueAdapter.submitList(it.filter { it.state })
                    }
                }
                launch {
                    homeViewModel.filteredIssueList.collect {
                        issueAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun settingRecyclerview() {
        issueAdapter = IssueAdapter()
        issueAdapter.issueAdapterEventListener = object : IssueAdapterEventListener {
            override fun updateIssueState(itemId: String, boolean: Boolean) {
                viewLifecycleOwner.lifecycleScope.launch {
                    homeViewModel.updateIssueSate(itemId, boolean)
                }
            }

            override fun switchToEditMode(itemId: String) {
                issueAdapter.isEditMode = true
                binding.clIssueOriginalModeTop.visibility = View.GONE
                binding.clIssueEditModeTop.visibility = View.VISIBLE
                issueAdapter.notifyDataSetChanged()
            }

            override fun switchToOriginMode() {
                issueAdapter.isEditMode = false
                binding.clIssueOriginalModeTop.visibility = View.VISIBLE
                binding.clIssueEditModeTop.visibility = View.GONE
            }

            override fun addInCheckList(issue: Issue) {
                issueViewModel.addSelectedIssue(issue)
            }

            override fun deleteInCheckList(issue: Issue) {
                issueViewModel.deleteSelectedIssue(issue)
            }

            override fun getIntoDetail(issue: Issue) {
                val intent = Intent(requireContext(), DetailIssueActivity::class.java)
                intent.putExtra("issue", issue)
                startActivity(intent)
            }
        }

        binding.rvIssue.adapter = issueAdapter
        binding.rvIssue.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val swipeHelperCallback = SwipeHelperCallback()
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvIssue)
    }

    private fun navigateFilterScreen() {
        binding.ivIssueMenu.setOnClickListener {
            navController.navigate(R.id.action_issueFragment2_to_filterFragment)
        }
    }

    private fun navigateIssueEditor() {
        binding.fabIssueEdit.setOnClickListener {
            navController.navigate(R.id.action_issueFragment2_to_issueEditor)
        }
    }
}

interface IssueAdapterEventListener {
    fun updateIssueState(itemId: String, boolean: Boolean)
    fun switchToEditMode(itemId: String)
    fun switchToOriginMode()
    fun addInCheckList(issue: Issue)
    fun deleteInCheckList(issue: Issue)
    fun getIntoDetail(issue: Issue)
}