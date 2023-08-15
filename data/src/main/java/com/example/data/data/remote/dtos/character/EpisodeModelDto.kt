package com.example.data.data.remote.dtos.character

import com.google.gson.annotations.SerializedName

data class EpisodeModelDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("episode")
    val episode: String
)
