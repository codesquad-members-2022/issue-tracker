package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelAddBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelAddFragment : Fragment() {

    private lateinit var binding: FragmentLabelAddBinding

    private val viewModel by viewModel<LabelAddViewModel>()

    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLabelAddBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.label_save -> {
                    viewModel.addLabel(
                        binding.editSubject.text.toString(),
                        binding.editDescription.text.toString(),
                        binding.editBackground.text.toString(),
                        viewModel.textColor.value
                    )
                    true
                }
                else -> false
            }
        }

        initView()
        observerData()
    }

    private fun initView() {
        binding.toolbar.setNavigationOnClickListener { popBackStack() }
        binding.editSubject.doAfterTextChanged { input ->
            viewModel.title.value = input.toString()
        }
    }

    private fun observerData() {
        job = lifecycleScope.launch {
            viewModel.completeSaveLabel.collect { complete ->
                if (complete) popBackStack()
            }
        }
    }

    private fun popBackStack() {
        parentFragmentManager.popBackStack()
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}