package com.example.it.issuetracker.presentation.main.milestone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMilestoneBinding
import com.example.it.issuetracker.presentation.main.label.LabelListAdapter

class MilestoneFragment : Fragment() {

    private lateinit var binding: FragmentMilestoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMilestoneBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }
}