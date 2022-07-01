package com.example.it.issuetracker.presentation.main.issue.register

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentRegisterIssueBinding
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.Constants.INIT_ERROR_MSG_ID
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import io.noties.markwon.Markwon
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.example.it.issuetracker.presentation.main.issue.filter.SpinnerAdapter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterIssueFragment :
    DataBindingBaseFragment<FragmentRegisterIssueBinding>(R.layout.fragment_register_issue) {

    private val imm: InputMethodManager by lazy { activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private lateinit var markOne: Markwon
    private var markDownFlag = false
    private var previousText = ""
    private val viewModel by viewModel<RegisterIssueViewModel>()
    private var editIssueDetail: IssueDetail? = null
    private var onRefresh: (() -> Unit)? = null

    fun setOnRefreshListener(listener: () -> Unit) {
        onRefresh = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        markOne = Markwon.builder(requireContext())
            .build()
        binding.lifecycleOwner = viewLifecycleOwner
        editIssueDetail = arguments?.getSerializable(Constants.ISSUE_BUNDLE_KEY) as? IssueDetail
        if (editIssueDetail != null) viewModel.setDataForEditIssue(editIssueDetail!!)

        initView()
        observerData()
    }

    override fun initView() {
        binding.editDescription.text.toString()
        setupSpinner()
        setupEditText()
        setupToolbar()
    }

    private fun getMarkDownView() {
        previousText = binding.editDescription.text.toString()
        binding.editDescription.isEnabled = false
        imm.hideSoftInputFromWindow(binding.editDescription.windowToken, 0)
        markOne.setMarkdown(binding.editDescription, binding.editDescription.text.toString())
        markDownFlag = true
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.new_issue_title_string)
        binding.toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
        binding.toolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.save_issue -> {
                    viewModel.saveIssue()
                    onRefresh?.invoke()
                    parentFragmentManager.popBackStack()
                    true
                }
                R.id.menu_preview -> {
                    if (!markDownFlag) getMarkDownView()
                    else getEditView()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupEditText() {
        binding.editSubject.doAfterTextChanged { input ->
            val text = input.toString()
            viewModel.setSubjectEmptyState(text.isEmpty())
            viewModel.setSubject(text)
        }
        binding.editDescription.doAfterTextChanged { input ->
            val text = input.toString()
            viewModel.setDescriptionEmptyState(text.isEmpty())
            viewModel.setDescription(text)
        }
    }

    private fun setupSpinner() {
        binding.filterMilestone.spinner.onItemSelectedListener =
            setSpinnerItemSelectedListener { index -> viewModel.setSelectedMilestoneIndex(index) }
        binding.filterAssignee.spinner.onItemSelectedListener =
            setSpinnerItemSelectedListener { index -> viewModel.setSelectedAssigneeIndex(index) }
    }

    private fun setSpinnerItemSelectedListener(listener: (position: Int) -> Unit): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = listener(position)

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    override fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.filterItems }
                .distinctUntilChanged()
                .collect { filterItems ->
                    createLabelChipItems(filterItems.labelList)

                    val milestoneTitleList =
                        filterItems.milestoneList.map { it.title }.toMutableList()
                    milestoneTitleList.add(0, "선택")
                    binding.filterMilestone.spinner.adapter =
                        SpinnerAdapter(requireContext(), R.layout.item_spinner, milestoneTitleList)

                    val assigneeNameList = filterItems.assigneeList.map { it.name }.toMutableList()
                    assigneeNameList.add(0, "선택")
                    binding.filterAssignee.spinner.adapter =
                        SpinnerAdapter(requireContext(), R.layout.item_spinner, assigneeNameList)
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.selectedFilters }
                .distinctUntilChanged()
                .collect {
                    if (it.labelIndex != null) checkedLabel(it.labelIndex)
                    if (it.milestoneIndex != null) binding.filterMilestone.spinner.setSelection(it.milestoneIndex.toInt())
                    if (it.assigneeIndex != null) binding.filterAssignee.spinner.setSelection(it.assigneeIndex)
                }
        }
        repeatOnLifecycleExtension {
            val saveMenu = binding.toolbar.menu.findItem(R.id.save_issue)
            viewModel.uiState.map { it.requiredField }
                .distinctUntilChanged()
                .collect {
                    saveMenu.isEnabled = !it.isEmptySubject && !it.isEmptyDescription
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.errorMsgId }
                .distinctUntilChanged()
                .collect { msgId ->
                    if (msgId == INIT_ERROR_MSG_ID) return@collect
                    val text = getString(msgId)
                    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun checkedLabel(labelList: List<Int>) {
        labelList.forEach { index ->
            binding.labelChipGroup.findViewWithTag<Chip>(index)?.apply {
                isChecked = true
            }
        }
    }

    private fun createLabelChipItems(labelList: List<Label>) {
        binding.labelChipGroup.removeAllViews()
        labelList.forEach { label ->
            binding.labelChipGroup.addView(createChip(requireContext(), label))
        }
    }

    private fun createChip(context: Context, label: Label): Chip {
        return Chip(context).apply {
            text = label.title
            chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(label.color))
            isCheckable = true
            tag = label.id
            setTextColor(Color.parseColor(label.textColor))
            setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.setCheckedLabel(buttonView.tag as Int, isChecked)
            }
        }
    }

    private fun getEditView() {
        with(binding) {
            editDescription.setText(previousText)
            editDescription.isEnabled = true
            editDescription.setSelection(editDescription.text.toString().length)
        }
        imm.showSoftInput(binding.editDescription, 0)
        previousText = ""
        markDownFlag = false
    }
}