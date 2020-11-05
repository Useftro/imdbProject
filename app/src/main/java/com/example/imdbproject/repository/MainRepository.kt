package com.example.imdbproject.repository

import com.example.imdbproject.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCharacters() = apiHelper.getCharacters()

}