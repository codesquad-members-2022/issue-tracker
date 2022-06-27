package com.example.issue_tracker.ui.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.common.repeatOnLifecycleExtension
import com.example.issue_tracker.databinding.FragmentIssueAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class IssueAddFragment : Fragment() {

    private lateinit var binding: FragmentIssueAddBinding

    private val viewModel: IssueAddViewModel by viewModels()

    private val labelPopUpMenu by lazy {
        val labelList = viewModel.labelList.value
        PopupMenu(requireContext(), binding.ibFilterButtonLabel).apply {
            for(index in labelList.indices) {
                menu.add(Menu.NONE, index, index, labelList[index].labelTitle)
            }
        }
    }

    private val mileStonePopUpMenu by lazy {
        val mileStoneList = viewModel.mileStoneList.value
        PopupMenu(requireContext(), binding.ibFilterButtonIssueMileStone).apply {
            for(index in mileStoneList.indices) {
                menu.add(Menu.NONE, index, index, mileStoneList[index].title)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_add, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val findNavController = findNavController()
        observeMenuButtons()
        findClickedMenu()
        observeClickedMenuText()
        goBackIssue(findNavController)
    }

    private fun observeMenuButtons() {
        with(binding) {
            ibFilterButtonLabel.setOnClickListener {
                labelPopUpMenu.show()
            }
            ibFilterButtonIssueMileStone.setOnClickListener {
                mileStonePopUpMenu.show()
            }
        }
    }

    private fun findClickedMenu() {
        labelPopUpMenu.setOnMenuItemClickListener { item ->
            viewModel.findClickedLabelMenu(item.itemId)
            false
        }
        mileStonePopUpMenu.setOnMenuItemClickListener { item ->
            viewModel.findClickedMileStoneMenu(item.itemId)
            false
        }
    }

    private fun observeClickedMenuText() {
        viewLifecycleOwner.repeatOnLifecycleExtension(Lifecycle.State.STARTED) {
            viewModel.labelChoose.collect {
                binding.label = it
            }
        }
        viewLifecycleOwner.repeatOnLifecycleExtension(Lifecycle.State.STARTED) {
            viewModel.mileStoneChoose.collect {
                binding.mileStone = it
            }
        }
    }

    private fun goBackIssue(findNavController: NavController) {
        binding.ivGoBack.setOnClickListener {
            findNavController.navigate(R.id.action_issueAddFragment_to_issueFragment)
        }
    }
}