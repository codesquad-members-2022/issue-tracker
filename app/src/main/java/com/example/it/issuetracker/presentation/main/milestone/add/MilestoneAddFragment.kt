package com.example.it.issuetracker.presentation.main.milestone.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMilestoneAddBinding
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.common.Constants
import com.example.it.issuetracker.presentation.common.DataBindingBaseFragment
import com.example.it.issuetracker.presentation.common.repeatOnLifecycleExtension
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MilestoneAddFragment :
    DataBindingBaseFragment<FragmentMilestoneAddBinding>(R.layout.fragment_milestone_add) {

    private val viewModel by viewModel<MilestoneAddViewModel>()
    private var editMilestoneInfo: MileStone? = null
    private var clickSaveListener: (() -> Unit)? = null
    fun setOnClickSaveListener(listener: () -> Unit) {
        clickSaveListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        editMilestoneInfo = arguments?.getSerializable("milestone") as? MileStone
        binding.viewModel = viewModel

        initView()
        observerData()
    }

    private fun addMilestone() {
        viewModel.addMilestone(
            binding.editSubject.text.toString(),
            binding.editDescription.text.toString(),
            binding.editCompleteDate.text.toString(),
        )
    }

    private fun editMilestone() {
        editMilestoneInfo?.let { editMilestone ->
            val milestoneInfo = editMilestone.copy(
                title = binding.editSubject.text.toString(),
                description = binding.editDescription.text.toString(),
                deadLine = binding.editCompleteDate.text.toString()
            )
            viewModel.editMilestone(milestoneInfo)
        }
    }

    override fun initView() {
        if (editMilestoneInfo == null) {
            binding.toolbarTitle = resources.getString(R.string.milestone_add_string)
        } else {
            binding.toolbarTitle = resources.getString(R.string.milestone_edit_string)
            viewModel.setData(editMilestoneInfo!!)
        }

        val saveMenu = binding.labelAppbarLayout.toolbar.menu.findItem(R.id.save_string)
        binding.labelAppbarLayout.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.save_string -> {
                    if (editMilestoneInfo == null) addMilestone()
                    else editMilestone()
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
        binding.editCompleteDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("날짜를 선택하세요")
                .build()
            datePicker.addOnPositiveButtonClickListener {
                val dateString = timestampToDateString(it)
                binding.editCompleteDate.text = dateString
                viewModel.setDeadline(dateString)
            }
            datePicker.show(parentFragmentManager, "datepicker")
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
        parentFragmentManager.popBackStack(
            "milestone_list",
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun timestampToDateString(timestamp: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.isLenient = false

        return format.format(Date(timestamp))
    }
}