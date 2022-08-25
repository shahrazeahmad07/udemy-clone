package com.example.udemyclone

import android.os.Parcel
import android.os.Parcelable

class CourseRVModal(val courseName: String?, val coursePrice: Double, val courseSuitedFor: String?, val courseImageLink: String?, val courseLink: String?, val courseDescription: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseName)
        parcel.writeDouble(coursePrice)
        parcel.writeString(courseSuitedFor)
        parcel.writeString(courseImageLink)
        parcel.writeString(courseLink)
        parcel.writeString(courseDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseRVModal> {
        override fun createFromParcel(parcel: Parcel): CourseRVModal {
            return CourseRVModal(parcel)
        }

        override fun newArray(size: Int): Array<CourseRVModal?> {
            return arrayOfNulls(size)
        }
    }

}