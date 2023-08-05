package com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character

import com.example.rickandmortyapicleanarchitecture.domain.models.LocationModel
import com.google.gson.annotations.SerializedName

data class LocationModelDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String
)

fun LocationModelDto.toDomain() = LocationModel(
    id, name, type, dimension
)
