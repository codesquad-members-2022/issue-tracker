package com.team1.issuetracker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.team1.issuetracker.R
import com.team1.issuetracker.databinding.ActivityLoginBinding
import com.team1.issuetracker.databinding.ActivityMainBinding
import com.team1.issuetracker.ui.main.issue.IssueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: IssueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.itemIconTintList = null
        val navController = supportFragmentManager.findFragmentById(R.id.container)?.findNavController()
        navController?.let{
            binding.bottomNavigation.setupWithNavController(it)
        }

        //observeActionMode()
    }

    /*private fun observeActionMode(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.actionModeFlow.collect{
                    if(it){
                        val actionMode = startSupportActionMode(viewModel.issueCallback)
                    }
                }
            }
        }
    }*/

}