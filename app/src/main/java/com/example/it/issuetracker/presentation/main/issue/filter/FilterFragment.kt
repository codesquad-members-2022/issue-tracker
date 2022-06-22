package com.example.it.issuetracker.presentation.main.issue.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentFilterBinding
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.list.IssueFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.Serializable

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding
    private val viewModel by sharedViewModel<FilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setupSpinner()
        setupSpinnerHandler()
        setEvent()
    }

    private fun setEvent() {
        binding.toolbarFilterIssue.setNavigationOnClickListener {
            parentFragmentManager.popBackStack("filter", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.btnReset.setOnClickListener {
            binding.filterState.spinner.setSelection(0)
            binding.filterLabel.spinner.setSelection(0)
            binding.filterWriter.spinner.setSelection(0)
            binding.filterMilestone.spinner.setSelection(0)
        }

        binding.btnApply.setOnClickListener {
            viewModel.getFilterList()
        }
    }

    private fun setupSpinner() {
        repeatOnLifecycleExtension {
            viewModel.labelList.collectLatest {
                val list = it.map { label -> label.title }
                binding.filterLabel.spinner.adapter =
                    SpinnerAdapter(requireContext(), R.layout.item_spinner, list)
                binding.filterLabel.spinner.setSelection(viewModel.labelIndex.value)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.state.collectLatest {
                binding.filterState.spinner.adapter =
                    SpinnerAdapter(requireContext(), R.layout.item_spinner, it)
                binding.filterState.spinner.setSelection(viewModel.stateIndex.value)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.writer.collectLatest {
                val writer = it.map { member -> member.name }
                binding.filterWriter.spinner.adapter =
                    SpinnerAdapter(requireContext(), R.layout.item_spinner, writer)
                binding.filterWriter.spinner.setSelection(viewModel.writerIndex.value)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.milestoneList.collectLatest {
                val milestone = it.map { milestone -> milestone.title }
                binding.filterMilestone.spinner.adapter =
                    SpinnerAdapter(requireContext(), R.layout.item_spinner, milestone)
                binding.filterMilestone.spinner.setSelection(viewModel.milestoneIndex.value)
            }
        }

        repeatOnLifecycleExtension {
            viewModel.eventApply.collectLatest {
                if (it) {
                    parentFragmentManager.popBackStack()
                    val issueFragment = IssueFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("filter", viewModel.issues.value as Serializable)
                    issueFragment.arguments = bundle
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container_main, issueFragment)
                        .commit()
                } else Toast.makeText(requireContext(), "필터를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinnerHandler() {
        binding.filterState.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    viewModel.clickFilterItem(
                        binding.filterState.spinner.selectedItem.toString(),
                        0, position
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }

        binding.filterWriter.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    viewModel.clickFilterItem(
                        binding.filterWriter.spinner.selectedItem.toString(),
                        1, position
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }

        binding.filterLabel.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    viewModel.clickFilterItem(
                        binding.filterLabel.spinner.selectedItem.toString(),
                        2, position
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }

        binding.filterMilestone.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    viewModel.clickFilterItem(
                        binding.filterMilestone.spinner.selectedItem.toString(),
                        3, position
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }
    }
}