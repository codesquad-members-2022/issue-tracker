package com.example.issu_tracker.ui.issue

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.issu_tracker.databinding.FragmentIssueEditorBinding
import io.noties.markwon.Markwon
import io.noties.markwon.editor.MarkwonEditor
import io.noties.markwon.editor.MarkwonEditorTextWatcher
import kotlinx.coroutines.launch


class IssueEditor : Fragment() {

    private lateinit var binding: FragmentIssueEditorBinding
    private lateinit var markwon: Markwon
    private val viewModel: IssueEditorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIssueEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tbIssueEditor.firstActionItem.isEnabled = true
        markwon = Markwon.create(this.requireActivity())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { displayTextInMarkdown() }
            }
        }

        markwon.setMarkdown(binding.tvIssueEditorBody, "_되나요_")
        addIssueBodyTextChangedListener()
        setOnPreviewEventListener()

    }

    private suspend fun displayTextInMarkdown() {
        viewModel.issueBodyStateFlow.collect {
            markwon.setMarkdown(binding.tvIssueEditorBody, it)
        }
    }

    private fun setOnPreviewEventListener() {
        binding.tbIssueEditor.firstActionItem.setOnMenuItemClickListener {
            binding.tvIssueEditorBody.isVisible = !binding.tvIssueEditorBody.isVisible
            binding.etIssueEditorBody.isVisible = !binding.etIssueEditorBody.isVisible
            true
        }
    }

    private fun addIssueBodyTextChangedListener() {
        binding.etIssueEditorBody.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(inputText: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(inputText: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(inputText: Editable?) {
                viewModel.saveIssueBodyText(inputText.toString())
            }
        })
    }


}