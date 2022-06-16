package com.example.issu_tracker.filter

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.issu_tracker.R

class FilterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }
}