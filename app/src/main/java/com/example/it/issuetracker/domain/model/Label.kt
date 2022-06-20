package com.example.it.issuetracker.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.presentation.main.issue.Mode

data class Label(
    val id: Int,
    val title: String,
    val description: String = "",
    val color: String,
    val textColor: String,
    var isChecked: Boolean = false,
    val mode: Mode = Mode.DEFAULT
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(color)
        parcel.writeString(textColor)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Label> {
        override fun createFromParcel(parcel: Parcel): Label {
            return Label(parcel)
        }

        override fun newArray(size: Int): Array<Label?> {
            return arrayOfNulls(size)
        }
    }
}

fun Label.toDto(): LabelDto {
    return LabelDto(id, title, description, color, textColor)
}