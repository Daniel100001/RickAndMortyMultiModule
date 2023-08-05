package com.example.rickandmortyapicleanarchitecture.presentation.models

import com.example.rickandmortyapicleanarchitecture.domain.models.LocationModel

data class LocationUIModel(

    val id: Int,

    val name: String,

    val type: String,

    val dimension: String
)

fun LocationModel.toUI() = LocationUIModel(
    id, name, type, dimension
)
