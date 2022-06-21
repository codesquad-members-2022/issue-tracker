package com.example.it.issuetracker.presentation.main.issue.filter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.it.issuetracker.databinding.ItemSpinnerBinding

class SpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val items: List<String>,
) : ArrayAdapter<String>(context, resId, items) {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): String = items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.item = items[position]
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.item = items[position]
        return binding.root
    }
}