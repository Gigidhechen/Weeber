package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

data class Num(
    val num: Int,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(num)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Num>{
        override fun createFromParcel(parcel: Parcel): Num {
            return Num(parcel)
        }

        override fun newArray(size: Int): Array<Num?> {
            return arrayOfNulls(size)
        }
    }
}