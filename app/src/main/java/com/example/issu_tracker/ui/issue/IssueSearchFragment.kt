package com.example.issu_tracker.ui.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.databinding.FragmentIssueSearchBinding
import com.example.issu_tracker.ui.home.HomeViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class IssueSearchFragment : Fragment() {

    private lateinit var binding: FragmentIssueSearchBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val issueSearchViewModel: IssueSearchViewModel by viewModels()
    private lateinit var issueSearchAdapter: IssueSearchAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIssueSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)
        issueSearchAdapter = IssueSearchAdapter()
        binding.rvIssueSearch.adapter = issueSearchAdapter

        initIssueList()
        setOnSearchIconEventListener()
        setNavigationIconEventListener()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { displaySearchedIssues() }
            }
        }
    }

    private fun initIssueList() {
        val originalIssueList = homeViewModel.issueListStateFlow.value
        issueSearchViewModel.initIssueList(originalIssueList)
    }

    private fun setNavigationIconEventListener() {
        binding.tbIssueSearch.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun setOnSearchIconEventListener() {
        binding.etlIssueSearch.setEndIconOnClickListener {
            val searchKeyword = binding.etIssueSearch.text.toString()
            issueSearchViewModel.filterIssueBySearch(searchKeyword)
        }
    }

    private suspend fun displaySearchedIssues() {
        issueSearchViewModel.searchedIssueStateFlow.collect {
            issueSearchAdapter.submitList(it)
        }
    }


}