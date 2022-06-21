package com.example.issue_tracker.ui.issue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.FragmentIssueFilterBinding


class IssueFilterFragment : Fragment() {

    lateinit var binding: FragmentIssueFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_filter, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val findNavController = findNavController()
        goBackIssue(findNavController)

        binding.ibFilterButtonStatus.setOnClickListener {
            val statusPopupMenu = PopupMenu(requireContext(), it)
            statusPopupMenu.menuInflater.inflate(R.menu.issue_filter_status_menu, statusPopupMenu.menu)
            statusPopupMenu.show()
        }
    }

    private fun goBackIssue(findNavController: NavController) {
        binding.btnCloseIssueFilter.setOnClickListener {
            findNavController.navigate(R.id.action_issueFilterFragment_to_issueFragment)
        }
    }
}