package com.team1.issuetracker.ui.main.label

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.team1.issuetracker.R
import com.team1.issuetracker.common.repeatOnStarted
import com.team1.issuetracker.databinding.FragmentAddLabelBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AddLabelFragment : Fragment() {

    private lateinit var binding: FragmentAddLabelBinding
    private val viewModel: AddLabelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddLabelBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.menu.getItem(0).isEnabled = false

        binding.viewModel = viewModel

        repeatOnStarted {
            viewModel.labelText.collect {
                binding.tvBadgeLabel.text = it
            }
        }

        repeatOnStarted {
            viewModel.saveButtonState.collect {
                binding.topAppBar.menu.getItem(0).isEnabled = it
            }
        }
        repeatOnStarted {
            viewModel.labelColor.collect {
                binding.cvBadgeLabel.setCardBackgroundColor(it)
            }
        }
        repeatOnStarted {
            viewModel.labelColorText.collect {
                binding.etBackground.setText(it)
            }
        }
        setClickSaveButton()
        setColorChangeButton()
        setBackButton()
    }

    private fun setClickSaveButton() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_save -> {
                    Log.d(
                        "TAG",
                        "setOnMenuItemClickListener  ${binding.topAppBar.menu.getItem(0).isEnabled}"
                    )
                    true
                }
                else -> {
                    false
                }

            }
        }
    }

    private fun setBackButton() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addLabelFragment_to_navigation_label)
        }
    }


    private fun setColorChangeButton() {
        binding.btnColorChange.setOnClickListener {
            viewModel.setLabelBackground()
        }
    }


}