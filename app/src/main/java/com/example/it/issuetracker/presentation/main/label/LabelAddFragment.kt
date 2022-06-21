package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelAddBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelAddFragment : Fragment() {

    private lateinit var binding: FragmentLabelAddBinding
    private val viewModel by viewModel<LabelAddViewModel>()
    private var editLabelInfo: Label? = null
    private var clickSaveListener: (() -> Unit)? = null

    fun setOnClickSaveListener(listener: () -> Unit) {
        clickSaveListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLabelAddBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        editLabelInfo = arguments?.getSerializable("label") as? Label

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.label_save -> {
                    if (editLabelInfo == null) {
                        addLabel()
                    } else {
                        editLabel()
                    }
                    true
                }
                else -> false
            }
        }

        initView()
        observerData()
    }

    private fun addLabel() {
        viewModel.addLabel(
            binding.editSubject.text.toString(),
            binding.editDescription.text.toString(),
            binding.editBackground.text.toString(),
            viewModel.textColor.value
        )
    }

    private fun editLabel() {
        editLabelInfo?.let { editLabel ->
            val labelInfo = editLabel.copy(
                title = binding.editSubject.text.toString(),
                description = binding.editDescription.text.toString(),
                color = binding.editBackground.text.toString(),
                textColor = viewModel.textColor.value
            )

            viewModel.editLabel(labelInfo)
        }
    }

    private fun initView() {
        editLabelInfo?.let {
            viewModel.setData(it)
        }

        val saveMenu = binding.toolbar.menu.findItem(R.id.label_save)
        binding.toolbar.setNavigationOnClickListener { popBackStack() }
        binding.editSubject.doAfterTextChanged { input ->
            val text = input.toString()
            saveMenu.isEnabled = text.isNotEmpty()
            viewModel.title.value = text
        }
    }

    private fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.completeSaveLabel.collect { complete ->
                if (complete) {
                    clickSaveListener?.invoke()
                    popBackStack()
                }
            }
        }
    }

    private fun popBackStack() {
        parentFragmentManager.popBackStack("label_list", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}