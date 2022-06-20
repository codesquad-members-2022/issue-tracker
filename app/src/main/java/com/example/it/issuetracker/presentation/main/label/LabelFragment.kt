package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelBinding
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelFragment : Fragment() {

    private lateinit var binding: FragmentLabelBinding
    private val viewModel by viewModel<LabelViewModel>()
    private val adapter: LabelListAdapter = LabelListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLabelBinding.inflate(inflater)
        binding.lifecycleOwner = this
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
    }

    private fun initView() = with(binding) {
        viewModel.getLabelInfoList()
        recyclerviewLabelItem.adapter = adapter
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_label -> {
                    parentFragmentManager.commit {
                        addToBackStack("label_list")
                        replace(R.id.container_main, LabelAddFragment())
                    }
                    true
                }
                else -> false
            }
        }
    }
}