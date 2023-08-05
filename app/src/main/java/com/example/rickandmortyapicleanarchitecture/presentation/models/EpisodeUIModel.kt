package com.example.rickandmortyapicleanarchitecture.presentation.models

import com.example.rickandmortyapicleanarchitecture.domain.models.EpisodeModel

data class EpisodeUIModel(

    val id: Int,

    val name: String,

    val episode: String
)

fun EpisodeUIModel.toUI() = EpisodeModel(
    id, name, episode
)