package com.example.issu_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.databinding.FragmentIssueBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class IssueFragment : Fragment() {
    private lateinit var binding: FragmentIssueBinding
    private val issueAdapter = IssueAdapter()
    private val homeViewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)

        settingRecyclerview()
        updateRecyclerview()

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
        binding.rvIssue.adapter = issueAdapter
        binding.rvIssue.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}