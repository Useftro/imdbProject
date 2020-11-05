package com.example.imdbproject.api

import com.example.imdbproject.data.model.CharacterModel
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): List<CharacterModel>
}