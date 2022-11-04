package com.example.weather.model.data

import android.os.Parcel
import android.os.Parcelable

data class LocationData(
    val locationName: String,
    val latitude: Float,
    val longitude: Float,
    val country: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(locationName)
        parcel.writeFloat(latitude)
        parcel.writeFloat(longitude)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationData> {
        override fun createFromParcel(parcel: Parcel): LocationData {
            return LocationData(parcel)
        }

        override fun newArray(size: Int): Array<LocationData?> {
            return arrayOfNulls(size)
        }
        val DefaultLocation = LocationData(
            locationName = "Екатеринбург",
            latitude = 56.8575f,
            longitude = 60.6125f,
            country = "RU"
        )
    }
}