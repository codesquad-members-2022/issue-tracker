package com.example.issue_tracker.ui.label

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentLabelAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LabelAddFragment : Fragment() {

    private lateinit var binding: FragmentLabelAddBinding
    private val viewModel: LabelAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_label_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        val findNavController = findNavController()

        labelColorChange()
        labelTextChange()
        setUpTextLabelError()
        setClickListener(findNavController)

    }

    private fun labelColorChange() {
        viewLifecycleOwner.repeatOnLifecycleExtension(Lifecycle.State.STARTED) {
            viewModel.label.collect {
                binding.label = it
            }
        }
    }

    private fun labelTextChange() {
        viewLifecycleOwner.repeatOnLifecycleExtension(Lifecycle.State.STARTED) {
            viewModel.labelTitle.collect {
                binding.customLabel.setLabelTitle(it)
            }
        }
    }

    private fun setClickListener(findNavController: NavController) {
        binding.btnLabelSave.setOnClickListener {
            viewModel.saveLabel()
            findNavController.navigate(R.id.action_labelAddFragment_to_labelFragment)
        }
    }

    private fun setUpTextLabelError() {
        binding.etLabelTitle.addTextChangedListener { text: Editable? ->
            binding.btnLabelSave.isEnabled = !(text == null || text.isEmpty())
        }
    }
}