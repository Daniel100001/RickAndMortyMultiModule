package com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character

import com.example.rickandmortyapicleanarchitecture.domain.models.EpisodeModel
import com.google.gson.annotations.SerializedName

data class EpisodeModelDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("episode")
    val episode: String
)

fun EpisodeModelDto.toDomain() = EpisodeModel(
    id, name, episode
)