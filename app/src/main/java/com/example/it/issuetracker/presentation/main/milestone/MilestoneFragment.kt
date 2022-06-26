package com.example.it.issuetracker.presentation.main.milestone

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMilestoneBinding
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.common.BaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.milestone.add.MilestoneAddFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MilestoneFragment : BaseFragment<FragmentMilestoneBinding>(R.layout.fragment_milestone) {

    private val viewModel by viewModel<MilestoneViewModel>()

    private val adapter = MilestoneAdapter({ viewModel.changeEditMode(it) }, { navigatePage(it) })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupToolbar()
        initView()
        observerData()
    }

    override fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.milestoneList.collectLatest {
                adapter.submitList(it)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.completeDelete.collect { complete ->
                if (complete) {
                    viewModel.getMilestoneInfoList()
                    viewModel.changeEditMode(false)
                }
            }
        }

        repeatOnLifecycleExtension {
            viewModel.error.collect { msgId ->
                val msg = getString(msgId)
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun initView() {
        binding.recyclerviewMilestoneItem.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        binding.recyclerviewMilestoneItem.addItemDecoration(dividerItemDecoration)
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbarLayout.defaultToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_blue_icon -> {
                    navigatePage(null)
                    true
                }
                else -> false
            }
        }

        binding.toolbarLayout.editToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_white_icon -> {
                    viewModel.deleteItems()
                    true
                }
                else -> false
            }
        }
        binding.toolbarLayout.editToolbar.setNavigationOnClickListener {
            viewModel.changeEditMode(
                false
            )
        }
    }

    private fun navigatePage(milestone: MileStone?) {
        val addFragment = MilestoneAddFragment()
        addFragment.setOnClickSaveListener { viewModel.getMilestoneInfoList() }
        parentFragmentManager.commit {
            addToBackStack("milestone_list")
            if (milestone == null) replace(R.id.container_main, addFragment)
            else {
                addFragment.arguments = bundleOf("milestone" to milestone)
                replace(R.id.container_main, addFragment)
            }
        }
    }
}