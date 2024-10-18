package com.example.weeber.data.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val gender: String?,
    val name: UserName?,
    val email: String?,
    val dob: UserDob?,
    val cell: String?,
    val picture: UserPicture?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(UserName::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(UserDob::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(UserPicture::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gender)
        parcel.writeParcelable(name, flags)
        parcel.writeString(email)
        parcel.writeParcelable(dob, flags)
        parcel.writeString(cell)
        parcel.writeParcelable(picture, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User>{
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}