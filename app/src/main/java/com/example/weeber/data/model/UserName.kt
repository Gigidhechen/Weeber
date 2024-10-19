package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

class UserName (
    val title: String?,
    val first: String?,
    val last: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(first)
        parcel.writeString(last)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserName>{
        override fun createFromParcel(parcel: Parcel): UserName {
            return UserName(parcel)
        }

        override fun newArray(size: Int): Array<UserName?> {
            return arrayOfNulls(size)
        }
    }

}
