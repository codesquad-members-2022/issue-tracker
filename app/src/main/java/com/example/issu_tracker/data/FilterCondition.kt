package com.example.issu_tracker.data

import android.os.Parcel
import android.os.Parcelable

data class FilterCondition(
    val state: String,
    val writer: String,
    val label: String,
    val mileStone: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(state)
        parcel.writeString(writer)
        parcel.writeString(label)
        parcel.writeString(mileStone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterCondition> {
        override fun createFromParcel(parcel: Parcel): FilterCondition {
            return FilterCondition(parcel)
        }

        override fun newArray(size: Int): Array<FilterCondition?> {
            return arrayOfNulls(size)
        }
    }

}
