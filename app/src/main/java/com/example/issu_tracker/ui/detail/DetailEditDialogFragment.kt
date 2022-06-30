package com.example.issu_tracker.ui.detail

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.issu_tracker.databinding.DetailEditDialogFragmentBinding
import java.lang.StringBuilder


class DetailEditDialogFragment(val labelNameList: List<String>, mileStoneName: String? = null) :
    DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    private lateinit var binding: DetailEditDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailEditDialogFragmentBinding.inflate(inflater, container, false)

        binding.tvLabelContent.text = labelNameList.joinToString(", ")
        return binding.root
    }


    override fun onResume() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        // DialogBackGround 에 기본 padding 이 있는 것으로 추정
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onResume()
    }
}

fun List<String>.stringListToText(separator: String = ", "): String {
    val stringBuilder = StringBuilder()

    this.forEach {
        stringBuilder.append(it)
        stringBuilder.append(separator)
    }
    stringBuilder.replace(
        stringBuilder.lastIndex - separator.length + 1,
        stringBuilder.lastIndex,
        ""
    )
    return stringBuilder.toString()
}