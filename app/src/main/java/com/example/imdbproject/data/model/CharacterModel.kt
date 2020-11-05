package com.example.imdbproject.data.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>
)