package com.example.issu_tracker.ui.issue

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.FragmentIssueEditorBinding
import com.example.issu_tracker.ui.common.Constants
import com.example.issu_tracker.ui.filter.FilterFragment
import com.example.issu_tracker.ui.filter.SpinnerAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon
import io.noties.markwon.image.ImagesPlugin
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IssueEditor : Fragment() {

    private lateinit var binding: FragmentIssueEditorBinding
    private lateinit var markwon: Markwon
    private lateinit var navController: NavController
    private val viewModel: IssueEditorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIssueEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)
        binding.tbIssueEditor.firstActionItem.isEnabled = true
        markwon = Markwon.builder(requireContext())
            .usePlugin(ImagesPlugin.create())
            .build();

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { inputIssueBody() }
                launch { inputIssueTitle() }
                launch { setAssigneeSpinner() }
                launch { setLabelSpinner() }
                launch { setMileStoneSpinner() }
            }
        }
        addIssueTitleTextChangedListener()
        addIssueBodyTextChangedListener()
        setOnPreviewEventListener()
        setNavigationIconEventListener()
    }

    private suspend fun inputIssueTitle() {
        viewModel.issueTitleStateFlow.collect {
            binding.etIssueEditorBody.isEnabled = (it.isNotEmpty() && it.isNotBlank())
            binding.tbIssueEditor.secondActionItem.isEnabled =
                (it.isNotEmpty() && it.isNotBlank() && viewModel.issueBodyStateFlow.value.isNotEmpty())
        }
    }

    private suspend fun inputIssueBody() {
        viewModel.issueBodyStateFlow.collect {
            displayBodyTextInMarkdown(it)
            setOnSaveEventListener(it)
        }
    }

    private fun setOnSaveEventListener(it: String) {
        binding.tbIssueEditor.secondActionItem.isEnabled = it.isNotEmpty()
        binding.tbIssueEditor.secondActionItem.setOnMenuItemClickListener {
//            viewModel.createIssue()
            navController.navigate(R.id.action_issueEditor_to_issueFragment2)
            true
        }
    }

    private fun displayBodyTextInMarkdown(it: String) {
        markwon.setMarkdown(binding.tvIssueEditorBody, it)
    }


    private fun setOnPreviewEventListener() {
        binding.tbIssueEditor.firstActionItem.setOnMenuItemClickListener {
            binding.tvIssueEditorBody.isVisible = !binding.tvIssueEditorBody.isVisible
            binding.etIssueEditorBody.isVisible = !binding.etIssueEditorBody.isVisible
            true
        }
    }

    private fun addIssueBodyTextChangedListener() {
        binding.etIssueEditorBody.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                inputText: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                inputText: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(inputText: Editable?) {
                viewModel.inputIssueBodyText(inputText.toString())
            }
        })
    }

    private fun addIssueTitleTextChangedListener() {
        binding.etIssueEditorTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                inputText: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                inputText: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(inputText: Editable?) {
                viewModel.inputIssueTitleText(inputText.toString())
            }
        })
    }

    private suspend fun setAssigneeSpinner() {
        viewModel.assigneeStateFlow.collect {
            setSpinner(binding.cbIssueEditorAssignee.spinner, it, Constants.CONDITION_TYPE_ASSIGNEE)
        }
    }

    private suspend fun setLabelSpinner() {
        viewModel.labelStateFlow.collect {
            setSpinner(binding.cbIssueEditorLabel.spinner, it, Constants.CONDITION_TYPE_LABEL)
        }
    }

    private suspend fun setMileStoneSpinner() {
        viewModel.mileStoneStateFlow.collect {
            setSpinner(
                binding.cbIssueEditorMilestone.spinner,
                it,
                Constants.CONDITION_TYPE_MILESTONE
            )
        }
    }

    private fun setSpinner(spinner: Spinner, list: List<String>, conditionType: Int) {
        val spinnerAdapter = SpinnerAdapter(this.requireContext(), R.layout.item_spinner, list)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(list.size - FilterFragment.SPINNER_DEFAULT_INDEX)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedView = view as TextView
                selectedView.setTextColor(ContextCompat.getColor(requireContext(), R.color.label2))
                viewModel.inputSpinnerValue(selectedView.text.toString(), conditionType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun setNavigationIconEventListener() {
        binding.tbIssueEditor.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}