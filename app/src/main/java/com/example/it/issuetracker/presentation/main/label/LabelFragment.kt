package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.label.add.LabelAddFragment
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelFragment : DataBindingBaseFragment<FragmentLabelBinding>(R.layout.fragment_label) {

    private val viewModel by viewModel<LabelViewModel>()
    private val adapter = LabelListAdapter({ viewModel.changeEditMode(it) }, { navigatePage(it) })

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
            viewModel.uiState.map { it.labelList }
                .distinctUntilChanged()
                .collect {
                    adapter.submitList(it)
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.completeTask }
                .distinctUntilChanged()
                .collect { isCompleted ->
                    if (isCompleted) {
                        viewModel.getLabelInfoList()
                        viewModel.changeEditMode(false)
                    }
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.errorMsgId }
                .distinctUntilChanged()
                .collect { msgId ->
                    if (msgId == Constants.INIT_ERROR_MSG_ID) return@collect
                    val msg = getString(msgId)
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun initView() {
        binding.recyclerviewLabelItem.adapter = adapter
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.recyclerviewLabelItem.adapter = adapter
        binding.recyclerviewLabelItem.layoutManager = LinearLayoutManager(requireContext())
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
            viewModel.changeEditMode(false)
        }
    }

    private fun navigatePage(label: Label?) {
        val addFragment = LabelAddFragment()
        addFragment.setOnClickSaveListener { viewModel.getLabelInfoList() }
        parentFragmentManager.commit {
            addToBackStack("label_list")
            if (label == null) replace(R.id.container_main, addFragment)
            else {
                addFragment.arguments = bundleOf("label" to label)
                replace(R.id.container_main, addFragment)
            }
        }
    }
}