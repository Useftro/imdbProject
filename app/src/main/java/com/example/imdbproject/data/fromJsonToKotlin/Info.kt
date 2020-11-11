package com.example.imdbproject.data.fromJsonToKotlin


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: @RawValue Any
): Parcelable