package com.example.issu_tracker.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.FragmentIssueBinding
import com.example.issu_tracker.databinding.FragmentMyAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyAccountFragment : Fragment() {
    private lateinit var binding: FragmentMyAccountBinding
    private val accountViewModel: AccountViewModel by viewModels()
    private lateinit var adapter: FriendAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_account, container, false)

        setRecyclerView()
        listenFriendListUpdate()


        return binding.root
    }

    private fun listenFriendListUpdate() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                accountViewModel.friendList.collect {
                    adapter.submitList(it)
                }
            }
        }
    }


    private fun setRecyclerView() {
        accountViewModel.loadFriendList()

        adapter = FriendAdapter()
        binding.rvFriendList.adapter = adapter
        binding.rvFriendList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}