package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelFragment : Fragment() {

    private lateinit var binding: FragmentLabelBinding
    private val viewModel by viewModel<LabelViewModel>()
    private val adapter = LabelListAdapter({ viewModel.changeEditMode(it) }, { navigatePage(it) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLabelBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupToolbar()
        initView()
        observerData()
    }

    private fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.labelList.collectLatest {
                adapter.submitList(it)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.completeDelete.collect { complete ->
                if (complete) {
                    viewModel.getLabelInfoList()
                    viewModel.changeEditMode(false)
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerviewLabelItem.adapter = adapter
        observerData()
        viewModel.start()
    }

    private fun setupToolbar() {
        binding.recyclerviewLabelItem.adapter = adapter
        binding.recyclerviewLabelItem.layoutManager = LinearLayoutManager(requireContext())
        binding.defaultToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_label -> {
                    navigatePage(null)
                    true
                }
                else -> false
            }
        }

        binding.editToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_label -> {
                    viewModel.deleteItems()
                    true
                }
                else -> false
            }
        }
        binding.editToolbar.setNavigationOnClickListener { viewModel.changeEditMode(false) }
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