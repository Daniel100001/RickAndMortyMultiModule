package com.example.data.data.remote.dtos.character

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
