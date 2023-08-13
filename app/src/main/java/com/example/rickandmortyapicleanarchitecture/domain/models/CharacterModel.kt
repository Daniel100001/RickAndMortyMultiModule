package com.example.rickandmortyapicleanarchitecture.domain.models

import com.example.rickandmortyapicleanarchitecture.data.local.room.entity.CharacterModelEntity

data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String
) : java.io.Serializable

fun CharacterModel.toEntity() = CharacterModelEntity(
    id, name, image, status, species, type, gender
)


