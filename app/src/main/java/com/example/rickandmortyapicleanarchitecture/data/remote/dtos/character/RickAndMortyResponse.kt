package com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T>(

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: List<T>,
)
