package com.example.rickandmortyapicleanarchitecture.domain.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String
) : java.io.Serializable


