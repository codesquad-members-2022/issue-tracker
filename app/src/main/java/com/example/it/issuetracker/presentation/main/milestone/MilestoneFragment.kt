package com.example.it.issuetracker.presentation.main.milestone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentMilestoneBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.toolbarLayout.defaultToolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.add_blue_icon -> {
                    val addFragment = MilestoneAddFragment()
                    parentFragmentManager.commit {
                        addToBackStack("milestone_add")
                        replace(R.id.container_main, addFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }
}