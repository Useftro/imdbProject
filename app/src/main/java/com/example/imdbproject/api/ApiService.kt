package com.example.imdbproject.api

import com.example.imdbproject.model.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): List<CharacterModel>
}