package com.example.it.issuetracker.presentation.main.label

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.FragmentLabelBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelFragment : Fragment() {

    private lateinit var binding: FragmentLabelBinding

    private val viewModel by viewModel<LabelViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLabelBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupToolbar()
        setupRecyclerView()
        viewModel.getLabelInfoList()
    }

    private fun setupRecyclerView() {
        binding.recyclerviewLabelItem.adapter = LabelListAdapter()
        binding.recyclerviewLabelItem.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_label -> {
                    parentFragmentManager.commit {
                        addToBackStack(null)
                        replace(R.id.container_main, LabelAddFragment())
                    }
                    true
                }
                else -> false
            }
        }
    }
}