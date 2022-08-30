package com.example.udemyclone

import android.os.Parcel
import android.os.Parcelable

class CourseRVModal() : Parcelable{
    var courseName: String? = null
    var coursePrice: Double? = null
    var courseSuitedFor: String? = null
    var courseImageLink: String? = null
    var courseLink: String? = null
    var courseDescription: String? = null

    constructor(parcel: Parcel) : this() {
        courseName = parcel.readString()
        coursePrice = parcel.readValue(Double::class.java.classLoader) as? Double
        courseSuitedFor = parcel.readString()
        courseImageLink = parcel.readString()
        courseLink = parcel.readString()
        courseDescription = parcel.readString()
    }

    constructor(courseName: String, coursePrice: Double, courseSuitedFor: String, courseImageLink: String, courseLink: String, courseDescription: String) : this() {
        this.courseName = courseName
        this.coursePrice = coursePrice
        this.courseSuitedFor = courseSuitedFor
        this.courseImageLink = courseImageLink
        this.courseLink = courseLink
        this.courseDescription = courseDescription
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseName)
        parcel.writeValue(coursePrice)
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