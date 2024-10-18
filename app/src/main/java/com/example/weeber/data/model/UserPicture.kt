package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

class UserPicture (
    val large: String?,
    val medium: String?,
    val small: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(large)
        parcel.writeString(medium)
        parcel.writeString(small)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserPicture> {
        override fun createFromParcel(parcel: Parcel): UserPicture {
            return UserPicture(parcel)
        }

        override fun newArray(size: Int): Array<UserPicture?> {
            return arrayOfNulls(size)
        }
    }

}
