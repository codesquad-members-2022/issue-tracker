package com.team1.issuetracker.ui.main.milestone

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.team1.issuetracker.common.repeatOnStarted
import com.team1.issuetracker.databinding.FragmentAddMilestoneBinding
import hirondelle.date4j.DateTime
import java.text.SimpleDateFormat
import java.util.*

class AddMilestoneFragment : Fragment() {

    private val binding: FragmentAddMilestoneBinding by lazy {
        FragmentAddMilestoneBinding.inflate(layoutInflater)
    }

    private val viewModel:AddMilestoneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        binding.topAppBar.menu.getItem(0).isEnabled = false

        repeatOnStarted {
            viewModel.saveButtonState.collect {
                binding.topAppBar.menu.getItem(0).isEnabled = it
            }
        }

        repeatOnStarted {
            viewModel.date.collect {
                binding.etDate.setText(it)
            }
        }

        setCalendarButton()

    }

    private fun setCalendarButton() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        binding.btnCalendar.setOnClickListener {
            datePicker.show(childFragmentManager, "calendar")

            datePicker.addOnPositiveButtonClickListener {
                val select = requireNotNull(datePicker.selection)
                val dateTime = DateTime.forInstant(select, TimeZone.getTimeZone("Asia/Seoul"))
                val dateFormat = dateTime.format("YYYY-MM-DD")
                viewModel.setDate(dateFormat)
            }
        }
    }
}