package com.rememberdev.exploresitubondo

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Destination(
    val name: String?,
    val location: String?,
    val description: String?,
    val open: String?,
    val ticket: String?,
    val photo1: Int?,
    val photo2: Int?,
    val photo3: Int?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeString(open)
        parcel.writeString(ticket)
        parcel.writeValue(photo1)
        parcel.writeValue(photo2)
        parcel.writeValue(photo3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Destination> {
        override fun createFromParcel(parcel: Parcel): Destination {
            return Destination(parcel)
        }

        override fun newArray(size: Int): Array<Destination?> {
            return arrayOfNulls(size)
        }
    }
}
