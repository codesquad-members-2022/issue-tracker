package com.team1.issuetracker.ui.main.milestone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.team1.issuetracker.R
import com.team1.issuetracker.common.repeatOnStarted
import com.team1.issuetracker.databinding.FragmentAddMilestoneBinding
import com.team1.issuetracker.databinding.FragmentMilestoneBinding
import com.team1.issuetracker.ui.main.label.AddLabelViewModel
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


        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        binding.btnCalendar.setOnClickListener {
            datePicker.show(childFragmentManager, "calendar")

            datePicker.addOnDismissListener {
                val select = requireNotNull(datePicker.selection)
                val stringDate = Date(select)
                val simpleDateFormat = SimpleDateFormat("YYYY-MM-DD")
                val dateFormat = simpleDateFormat.format(select)
                viewModel.setDate(dateFormat)
                Log.d("TAG", "date $dateFormat, $stringDate")
            }

        }
    }

}