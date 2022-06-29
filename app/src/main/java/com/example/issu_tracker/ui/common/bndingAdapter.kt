package com.example.issu_tracker.ui.common

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Label


@SuppressLint("Range")
@BindingAdapter("labelList")
fun setLabels(view: LinearLayout, list: List<Label>) {

    list.forEach {
        val textView =
            TextView(view.context)
        val background = ContextCompat.getDrawable(
            view.context,
            R.drawable.round_rectangle_background
        ) as GradientDrawable
        background.setColor(Color.parseColor(it.color))
        textView.background = background
        textView.text = it.content
        textView.setTextColor(Color.WHITE)
        view.addView(textView as View)

        // 여백을 통한 마진 생성
        // 아직 그려지지 않은 뷰에 대한 마진 값을 형성하는 것 보다 space view 를 추가하는 것이 쉽다고 판단
        val spaceView =
            View(view.context)
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(20, 0)
        spaceView.layoutParams = params

        view.addView(spaceView)
    }


}

@BindingAdapter("image")
fun setImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .circleCrop()
        .into(view)
}

@BindingAdapter("labelListOnRecyclerview")
fun setLabelsOnRecyclerview(view: RecyclerView, list: List<Label>) {
    val adapter = LabelAdapter(list)
    view.adapter = adapter

    view.layoutManager =
        LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
}

