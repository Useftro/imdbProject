package com.example.imdbproject.data.fromJsonToKotlin


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Result(
    val created: String = "",
    val episode: @RawValue List<Any>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String="f",
    val url: String
) : Parcelable