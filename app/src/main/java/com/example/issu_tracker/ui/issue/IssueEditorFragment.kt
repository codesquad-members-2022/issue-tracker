package com.example.issu_tracker.ui.issue

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.FragmentIssueEditorBinding
import com.example.issu_tracker.ui.common.Constants
import com.example.issu_tracker.ui.filter.FilterFragment
import com.example.issu_tracker.ui.filter.SpinnerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon
import io.noties.markwon.image.ImagesPlugin
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class IssueEditor : Fragment() {

    private lateinit var binding: FragmentIssueEditorBinding
    private lateinit var markwon: Markwon
    private lateinit var navController: NavController
    private val viewModel: IssueEditorViewModel by viewModels()
    private lateinit var launcherToGetImageFromGallery: ActivityResultLauncher<Intent>
    private lateinit var launcherRequestPermission: ActivityResultLauncher<Array<String>>
    private var startSelectionOfIssueBody = 0
    private var endSelectionOfIssueBody = 0
    private var ssbForIssueBody: SpannableStringBuilder = SpannableStringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)
        binding.tbIssueEditor.firstActionItem.isEnabled = true
        launcherToGetImageFromGallery = registerImageFromGalleryLauncher()
        launcherRequestPermission = registerRequestPermissionLauncher()
        markwon = Markwon.builder(requireContext())
            .usePlugin(ImagesPlugin.create())
            .build();

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { inputIssueBody() }
                launch { inputIssueTitle() }
                launch { setAssigneeSpinner() }
                launch { setLabelSpinner() }
                launch { setMileStoneSpinner() }
                launch { inputImageUrlFromFireBaseInEditText() }
            }
        }
        addIssueTitleTextChangedListener()
        addIssueBodyTextChangedListener()
        setOnPreviewEventListener()
        setNavigationIconEventListener()
        setIssueBodyLongClickEventListener()
        setEditTextActionModeCallbackDisabled()
    }


    private suspend fun inputIssueTitle() {
        viewModel.issueTitleStateFlow.collect {
            binding.etIssueEditorBody.isEnabled = (it.isNotEmpty() && it.isNotBlank())
            binding.tbIssueEditor.secondActionItem.isEnabled =
                (it.isNotEmpty() && it.isNotBlank() && viewModel.issueBodyStateFlow.value.isNotEmpty())
        }
    }

    private suspend fun inputIssueBody() {
        viewModel.issueBodyStateFlow.collect {
            displayBodyTextInMarkdown(it)
            setOnSaveEventListener(it)
        }
    }

    private fun setOnSaveEventListener(it: String) {
        binding.tbIssueEditor.secondActionItem.isEnabled = it.isNotEmpty()
        binding.tbIssueEditor.secondActionItem.setOnMenuItemClickListener {
//            viewModel.createIssue()
            navController.navigate(R.id.action_issueEditor_to_issueFragment2)
            true
        }
    }

    private fun displayBodyTextInMarkdown(it: String) {
        markwon.setMarkdown(binding.tvIssueEditorBody, it)
    }


    private fun setOnPreviewEventListener() {
        binding.tbIssueEditor.firstActionItem.setOnMenuItemClickListener {
            binding.tvIssueEditorBody.isVisible = !binding.tvIssueEditorBody.isVisible
            binding.etIssueEditorBody.isVisible = !binding.etIssueEditorBody.isVisible
            true
        }
    }

    private fun addIssueBodyTextChangedListener() {
        binding.etIssueEditorBody.doAfterTextChanged { changedText: Editable? ->
            viewModel.inputIssueBodyText(changedText.toString())
        }
    }

    private fun addIssueTitleTextChangedListener() {
        binding.etIssueEditorTitle.doAfterTextChanged { changedText: Editable? ->
            viewModel.inputIssueTitleText(changedText.toString())
        }
    }

    private suspend fun setAssigneeSpinner() {
        viewModel.assigneeStateFlow.collect {
            setSpinner(binding.cbIssueEditorAssignee.spinner, it, Constants.CONDITION_TYPE_ASSIGNEE)
        }
    }

    private suspend fun setLabelSpinner() {
        viewModel.labelStateFlow.collect {
            setSpinner(binding.cbIssueEditorLabel.spinner, it, Constants.CONDITION_TYPE_LABEL)
        }
    }

    private suspend fun setMileStoneSpinner() {
        viewModel.mileStoneStateFlow.collect {
            setSpinner(
                binding.cbIssueEditorMilestone.spinner,
                it,
                Constants.CONDITION_TYPE_MILESTONE
            )
        }
    }

    private fun setSpinner(spinner: Spinner, list: List<String>, conditionType: Int) {
        val spinnerAdapter = SpinnerAdapter(this.requireContext(), R.layout.item_spinner, list)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(list.size - FilterFragment.SPINNER_DEFAULT_INDEX)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedView = view as TextView
                selectedView.setTextColor(ContextCompat.getColor(requireContext(), R.color.label2))
                viewModel.inputSpinnerValue(selectedView.text.toString(), conditionType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun setNavigationIconEventListener() {
        binding.tbIssueEditor.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun setIssueBodyLongClickEventListener() {
        binding.etIssueEditorBody.setOnLongClickListener {
            startEditTextCustomActionMode()
            true
        }
    }

    private fun startEditTextCustomActionMode() {
        binding.etIssueEditorBody.startActionMode(object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                val inflater: MenuInflater? = mode?.menuInflater
                inflater?.inflate(R.menu.issue_editor_context_menu, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                startSelectionOfIssueBody = binding.etIssueEditorBody.selectionStart
                endSelectionOfIssueBody = binding.etIssueEditorBody.selectionEnd
                ssbForIssueBody = SpannableStringBuilder(binding.etIssueEditorBody.text)

                val clipboard =
                    requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

                when (item?.itemId) {
                    R.id.copy -> {
                        val copyClip =
                            ClipData.newPlainText(
                                "copy",
                                ssbForIssueBody.subSequence(
                                    startSelectionOfIssueBody,
                                    endSelectionOfIssueBody
                                )
                            )
                        clipboard.setPrimaryClip(copyClip)
                        mode?.finish()
                        return true
                    }
                    R.id.cut -> {
                        val cutClip = ClipData.newPlainText(
                            "cut",
                            ssbForIssueBody.subSequence(
                                startSelectionOfIssueBody,
                                endSelectionOfIssueBody
                            )
                        )
                        ssbForIssueBody.delete(startSelectionOfIssueBody, endSelectionOfIssueBody)
                        clipboard.setPrimaryClip(cutClip)
                        binding.etIssueEditorBody.text = ssbForIssueBody
                        mode?.finish()
                        return true
                    }
                    R.id.paste -> {
                        val clipData: CharSequence = clipboard.primaryClip?.getItemAt(0)?.text ?: ""
                        pasteString(clipData)
                        mode?.finish()
                        return true
                    }
                    R.id.insert_photo -> {
                        getPhotoUriFromGallery()
                        mode?.finish()
                        return true
                    }
                }
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

        }, ActionMode.TYPE_FLOATING)
    }

    private fun pasteString(text: CharSequence) {
        ssbForIssueBody.replace(startSelectionOfIssueBody, endSelectionOfIssueBody, text)
        binding.etIssueEditorBody.text = ssbForIssueBody
    }

    private suspend fun inputImageUrlFromFireBaseInEditText() {
        viewModel.imageUrlFromFireBaseStateFlow.collect {
            ssbForIssueBody = SpannableStringBuilder(binding.etIssueEditorBody.text)
            pasteString(it)
        }
    }

    private fun setEditTextActionModeCallbackDisabled() {
        binding.etIssueEditorBody.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

        }
    }

    private fun isAllPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all { permission ->
        ContextCompat.checkSelfPermission(this.requireContext(), permission) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun registerRequestPermissionLauncher() =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach { permission ->
                when {
                    permission.value -> {
                        Snackbar.make(binding.root, "Permission granted", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    shouldShowRequestPermissionRationale(permission.key) -> {
                        //Setting 페이지 이동
                    }
                    else -> {
                        Snackbar.make(binding.root, "Permission denied", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    private fun getPhotoUriFromGallery() {
        if (isAllPermissionsGranted()) {
            launchToGetPhotoFromGallery()
        } else {
            launcherRequestPermission.launch(REQUIRED_PERMISSIONS)
        }
    }

    private fun launchToGetPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        launcherToGetImageFromGallery.launch(intent)
    }

    private fun registerImageFromGalleryLauncher() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == RESULT_OK) {
                val currentImageUri = it.data?.data

                try {
                    currentImageUri?.let { uri ->
                        val imagePath: String = getPathOfUri(uri)
                        val file = Uri.fromFile(File(imagePath))
                        viewModel.uploadImageInFireBase(file.toString())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    @SuppressLint("Range")
    private fun getPathOfUri(uri: Uri): String {
        val cursor: Cursor? = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.moveToNext()
        val path: String? = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor?.close()
        return path ?: ""
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    }

}