package com.example.imdbproject.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCharacters() = apiService.getCharacters()

}