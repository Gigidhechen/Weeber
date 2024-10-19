package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

class NumResponse(
    val results: ArrayList<Num>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Num.CREATOR),

        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NumResponse>{
        override fun createFromParcel(parcel: Parcel): NumResponse {
            return NumResponse(parcel)
        }

        override fun newArray(size: Int): Array<NumResponse?> {
            return arrayOfNulls(size)
        }
    }
}