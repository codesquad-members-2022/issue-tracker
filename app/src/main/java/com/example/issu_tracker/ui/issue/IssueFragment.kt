package com.example.issu_tracker.ui.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.SwipeHelperCallback
import com.example.issu_tracker.data.FilterCondition
import com.example.issu_tracker.databinding.FragmentIssueBinding
import com.example.issu_tracker.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IssueFragment : Fragment() {
    private lateinit var binding: FragmentIssueBinding
    private lateinit var issueAdapter: IssueAdapter
    private val homeViewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)

        val conditions = arguments?.getParcelable<FilterCondition>("filterCondition")
        println(conditions)

        settingRecyclerview()
        updateRecyclerview()
        navigateFilterScreen()
        return binding.root
    }

    private fun updateRecyclerview() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.issueList.collect {
                    issueAdapter.submitList(it)
                }
            }
        }
    }

    private fun settingRecyclerview() {
        issueAdapter = IssueAdapter()
        issueAdapter.swipeDeleteListener = object : SwipeDeleteListener {
            override fun deleteItem(itemId: String) {
              viewLifecycleOwner.lifecycleScope.launch {
                  homeViewModel.deleteIssue(itemId)
              }
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
            val navController = Navigation.findNavController(binding.root)
            navController.navigate(R.id.action_issueFragment2_to_filterFragment)
        }
    }
}

interface SwipeDeleteListener {
    fun deleteItem(itemId: String)
}