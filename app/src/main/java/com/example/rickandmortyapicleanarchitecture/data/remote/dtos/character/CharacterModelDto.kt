package com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character

import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterModelDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("gender")
    val gender: String
) : java.io.Serializable

fun CharacterModelDto.toDomain() = CharacterModel(
    id, name, image, status, species, type, gender
)




