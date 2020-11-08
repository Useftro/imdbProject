package com.example.imdbproject.api

import com.example.imdbproject.data.fromJsonToKotlin.serverResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("character")
    fun getCharacters(@Query("page") page: Int): Call<serverResponse>

}