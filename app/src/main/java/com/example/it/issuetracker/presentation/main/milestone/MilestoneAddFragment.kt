package com.example.it.issuetracker.presentation.main.milestone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMilestoneAddBinding

class MilestoneAddFragment : Fragment() {

    private lateinit var binding: FragmentMilestoneAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMilestoneAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.toolbarTitle = resources.getString(R.string.milestone_toolbar_new_string)
    }
}