package com.example.issu_tracker.ui.common

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.ColorInt
import com.example.issu_tracker.R
import com.example.issu_tracker.data.ConditionType
import com.example.issu_tracker.ui.filter.FilterFragment

class SpinnerAdapter(context: Context, spinnerItemView: Int, private val list: List<String>) :
    ArrayAdapter<String>(context, spinnerItemView, list) {

    override fun getCount(): Int {
        //마지막 아이템은 힌트용(Place Holder)으로만 사용하기 때문에 getCount 에 1을 빼줍니다.
        return super.getCount() - 1
    }
}


fun Spinner.setSpinner(context: Context, list: List<String>, conditionType: Enum<ConditionType>, viewModel: SpinnerViewModel, @ColorInt color: Int) {
    val spinnerAdapter = SpinnerAdapter(context, R.layout.item_spinner, list)
    this.adapter = spinnerAdapter
    this.setSelection(list.size - FilterFragment.SPINNER_DEFAULT_INDEX)
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            val selectedView = view as TextView
            selectedView.setTextColor(color)
            viewModel.inputSpinnerValue(selectedView.text.toString(), conditionType)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

}