package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelAddBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.BaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelAddFragment : BaseFragment<FragmentLabelAddBinding>(R.layout.fragment_label_add) {

    private val viewModel by viewModel<LabelAddViewModel>()
    private var editLabelInfo: Label? = null
    private var clickSaveListener: (() -> Unit)? = null

    fun setOnClickSaveListener(listener: () -> Unit) {
        clickSaveListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        editLabelInfo = arguments?.getSerializable("label") as? Label
        binding.viewModel = viewModel
        binding.labelAppbarLayout.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save_string -> {
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

    override fun initView() {
        if (editLabelInfo == null) {
            binding.toolbarTitle = resources.getString(R.string.label_add_string)
        } else {
            binding.toolbarTitle = resources.getString(R.string.label_edit_string)
            viewModel.setData(editLabelInfo!!)
        }

        val saveMenu = binding.labelAppbarLayout.toolbar.menu.findItem(R.id.save_string)
        binding.labelAppbarLayout.toolbar.setNavigationOnClickListener { popBackStack() }
        binding.editSubject.doAfterTextChanged { input ->
            val text = input.toString()
            saveMenu.isEnabled = text.isNotEmpty()
            viewModel.title.value = text
        }
    }

    override fun observerData() {
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