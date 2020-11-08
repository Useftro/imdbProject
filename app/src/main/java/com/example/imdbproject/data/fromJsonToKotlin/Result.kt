package com.example.imdbproject.data.fromJsonToKotlin


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Result(
    val created: String,
    val episode: @RawValue List<Any>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: @RawValue Location,
    val name: String,
    val origin: @RawValue Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable