package com.example.issu_tracker.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.FragmentFilterBinding
import com.example.issu_tracker.ui.common.Constants.FILTER_TYPE_LABEL_CONDITION
import com.example.issu_tracker.ui.common.Constants.FILTER_TYPE_MILESTONE_CONDITION
import com.example.issu_tracker.ui.common.Constants.FILTER_TYPE_STATE_CONDITION
import com.example.issu_tracker.ui.common.Constants.FILTER_TYPE_WRITER_CONDITION
import com.example.issu_tracker.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterFragment : Fragment() {

    private val viewModel: FilterViewModel by viewModels()
    private lateinit var binding: FragmentFilterBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setStateSpinner() }
                launch { setWriterSpinner() }
                launch { setLabelSpinner() }
                launch { setMileStoneSpinner() }
                launch { collectConditionValue() }
            }
        }
        setFirstActionItemClickEvent()
        setSecondActionItemClickEvent()
    }

    private suspend fun setStateSpinner() {
        viewModel.stateStateFlow.collect {
            setSpinner(binding.cbFilterState.spinner, it, FILTER_TYPE_STATE_CONDITION)
        }
    }

    private suspend fun setWriterSpinner() {
        viewModel.writerStateFlow.collect {
            setSpinner(binding.cbFilterWriter.spinner, it, FILTER_TYPE_WRITER_CONDITION)
        }
    }

    private suspend fun setLabelSpinner() {
        viewModel.labelStateFlow.collect {
            setSpinner(binding.cbFilterLabel.spinner, it, FILTER_TYPE_LABEL_CONDITION)
        }
    }

    private suspend fun setMileStoneSpinner() {
        viewModel.mileStoneStateFlow.collect {
            setSpinner(binding.cbFilterMileStone.spinner, it, FILTER_TYPE_MILESTONE_CONDITION)
        }
    }

    private suspend fun collectConditionValue() {
        viewModel.conditionValueStateFlow.collect {
            setActionItemsEnabled(it)
        }
    }

    private fun setActionItemsEnabled(boolean: Boolean) {
        binding.tbFilter.firstActionItem.isEnabled = boolean
        binding.tbFilter.secondActionItem.isEnabled = boolean
    }

    private fun setFirstActionItemClickEvent() {
        binding.tbFilter.firstActionItem.setOnMenuItemClickListener {
            viewModel.resetConditionValues()
            binding.cbFilterState.spinner.setSelection(viewModel.stateStateFlow.value.size - SPINNER_DEFAULT_INDEX)
            binding.cbFilterWriter.spinner.setSelection(viewModel.writerStateFlow.value.size - SPINNER_DEFAULT_INDEX)
            binding.cbFilterLabel.spinner.setSelection(viewModel.labelStateFlow.value.size - SPINNER_DEFAULT_INDEX)
            binding.cbFilterMileStone.spinner.setSelection(viewModel.mileStoneStateFlow.value.size - SPINNER_DEFAULT_INDEX)
            true
        }
    }

    private fun setSecondActionItemClickEvent() {
        binding.tbFilter.secondActionItem.setOnMenuItemClickListener {
            val savedValues = viewModel.saveConditionValues()
            navController.navigate(
                R.id.action_filterFragment_to_issueFragment2,
                bundleOf("filterCondition" to savedValues)
            )
            true
        }
    }

    private fun setSpinner(spinner: Spinner, list: List<String>, conditionType: Int) {
        val spinnerAdapter = SpinnerAdapter(this.requireContext(), R.layout.item_spinner, list)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(list.size - SPINNER_DEFAULT_INDEX)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedView = view as TextView
                selectedView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                viewModel.inputSpinnerValue(selectedView.text.toString(), conditionType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    companion object {
        const val SPINNER_DEFAULT_INDEX = 1
    }


}