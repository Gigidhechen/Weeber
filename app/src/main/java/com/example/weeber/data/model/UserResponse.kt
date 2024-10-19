package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

class UserResponse(
    val results: ArrayList<User>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(User.CREATOR),

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserResponse>{
        override fun createFromParcel(parcel: Parcel): UserResponse {
            return UserResponse(parcel)
        }

        override fun newArray(size: Int): Array<UserResponse?> {
            return arrayOfNulls(size)
        }
    }
}