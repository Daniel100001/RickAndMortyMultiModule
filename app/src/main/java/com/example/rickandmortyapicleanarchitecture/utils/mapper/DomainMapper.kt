package com.example.rickandmortyapicleanarchitecture.utils.mapper

import com.example.rickandmortyapicleanarchitecture.data.local.room.entity.CharacterModelEntity
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel

object CharacterEntityMapper : Mapper<CharacterModelEntity,CharacterModel> {
    override fun toModel(value: CharacterModelEntity) = CharacterModel(
        value.id,
        value.name,
        value.image,
        value.status,
        value.species,
        value.type,
        value.gender
    )

    override fun fromModel(value: CharacterModel) = CharacterModelEntity(
        value.id,
        value.name,
        value.image,
        value.status,
        value.species,
        value.type,
        value.gender
    )
}