package com.example.issu_tracker.filter

import android.content.Context
import android.widget.ArrayAdapter

class SpinnerAdapter(context: Context, spinnerItemView: Int, private val list: List<String>) :
    ArrayAdapter<String>(context, spinnerItemView, list) {

    override fun getCount(): Int {
        //마지막 아이템은 힌트용(Place Holder)으로만 사용하기 때문에 getCount에 1을 빼줍니다.
        return super.getCount() - 1
    }


}