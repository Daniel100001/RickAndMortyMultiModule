package com.example.rickandmortyapicleanarchitecture.utils.mapper

import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.presentation.models.CharacterUIModel

object CharacterMapperUI : Mapper<CharacterModel,CharacterUIModel> {
    override fun toModel(value: CharacterModel) = CharacterUIModel(
        value.id,
        value.name,
        value.image,
        value.status,
        value.species,
        value.type,
        value.gender
    )

    override fun fromModel(value: CharacterUIModel) = CharacterModel(
        value.id,
        value.name,
        value.image,
        value.status,
        value.species,
        value.type,
        value.gender
    )
}