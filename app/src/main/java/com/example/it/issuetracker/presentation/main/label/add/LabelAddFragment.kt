package com.example.it.issuetracker.presentation.main.label.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelAddBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelAddFragment :
    DataBindingBaseFragment<FragmentLabelAddBinding>(R.layout.fragment_label_add) {

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

        initView()
        observerData()
    }

    private fun addLabel() {
        viewModel.addLabel(
            binding.editSubject.text.toString(),
            binding.editDescription.text.toString(),
            binding.editBackground.text.toString(),
            viewModel.uiState.value.textColor
        )
    }

    private fun editLabel() {
        editLabelInfo?.let { editLabel ->
            val labelInfo = editLabel.copy(
                title = binding.editSubject.text.toString(),
                description = binding.editDescription.text.toString(),
                color = binding.editBackground.text.toString(),
                textColor = viewModel.uiState.value.textColor
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
        binding.labelAppbarLayout.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save_string -> {
                    if (editLabelInfo == null) addLabel()
                    else editLabel()
                    true
                }
                else -> false
            }
        }
        binding.labelAppbarLayout.toolbar.setNavigationOnClickListener { popBackStack() }
        binding.editSubject.doAfterTextChanged { input ->
            val text = input.toString()
            saveMenu.isEnabled = text.isNotEmpty()
            viewModel.setTitle(text)
        }
        binding.editDescription.doAfterTextChanged { input ->
            val text = input.toString()
            viewModel.setDescription(text)
        }
    }

    override fun observerData() {
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.completeTask }
                .distinctUntilChanged()
                .collect { isCompleted ->
                    if (isCompleted) {
                        clickSaveListener?.invoke()
                        popBackStack()
                    }
                }
        }
        repeatOnLifecycleExtension {
            viewModel.uiState.map { it.errorMsgId }
                .distinctUntilChanged()
                .collect { msgId ->
                    if (msgId == Constants.INIT_ERROR_MSG_ID) return@collect
                    val msg = getString(msgId)
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun popBackStack() {
        parentFragmentManager.popBackStack("label_list", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}