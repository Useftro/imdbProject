package com.example.imdbproject.data.fromJsonToKotlin


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class serverResponse(
    val info: @RawValue Info = Info(0, "", 0, ""),
    val results: @RawValue ArrayList<Result> = ArrayList()
): Parcelable