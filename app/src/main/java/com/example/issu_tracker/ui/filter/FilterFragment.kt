package com.example.issu_tracker.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.issu_tracker.R
import com.example.issu_tracker.data.ConditionType
import com.example.issu_tracker.databinding.FragmentFilterBinding
import com.example.issu_tracker.ui.common.setSpinner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterFragment : Fragment() {

    private val viewModel: FilterViewModel by viewModels()
    private lateinit var binding: FragmentFilterBinding
    private lateinit var navController: NavController
    private var spinnerColor: Int = 0

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
        spinnerColor = ContextCompat.getColor(requireContext(), R.color.white)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { setStateSpinner() }
                launch { setWriterSpinner() }
                launch { setLabelSpinner() }
                launch { setMileStoneSpinner() }
                launch { collectConditionValue() }
            }
        }
        setNavigationIconEventListener()
        setFirstActionItemClickEvent()
        setSecondActionItemClickEvent()
    }

    private fun setNavigationIconEventListener() {
        binding.tbFilter.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private suspend fun setStateSpinner() {
        viewModel.stateStateFlow.collect {
           binding.cbFilterState.spinner.setSpinner(this.requireContext(), it, ConditionType.STATE, viewModel, spinnerColor)
        }
    }

    private suspend fun setWriterSpinner() {
        viewModel.writerStateFlow.collect {
            binding.cbFilterWriter.spinner.setSpinner(this.requireContext(), it, ConditionType.WRITER, viewModel, spinnerColor)
        }
    }

    private suspend fun setLabelSpinner() {
        viewModel.labelStateFlow.collect {
            binding.cbFilterLabel.spinner.setSpinner(this.requireContext(), it, ConditionType.LABEL, viewModel, spinnerColor)
        }
    }

    private suspend fun setMileStoneSpinner() {
        viewModel.mileStoneStateFlow.collect {
            binding.cbFilterMilestone.spinner.setSpinner(this.requireContext(), it, ConditionType.MILESTONE, viewModel, spinnerColor)
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
            binding.cbFilterMilestone.spinner.setSelection(viewModel.mileStoneStateFlow.value.size - SPINNER_DEFAULT_INDEX)
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

    companion object {
        const val SPINNER_DEFAULT_INDEX = 1
    }


}