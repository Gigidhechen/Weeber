package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

class UserDob (
    val date: String?,
    val age: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDob> {
        override fun createFromParcel(parcel: Parcel): UserDob {
            return UserDob(parcel)
        }

        override fun newArray(size: Int): Array<UserDob?> {
            return arrayOfNulls(size)
        }
    }

}
