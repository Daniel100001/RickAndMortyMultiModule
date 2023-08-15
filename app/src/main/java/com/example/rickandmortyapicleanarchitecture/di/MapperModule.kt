package com.example.rickandmortyapicleanarchitecture.di

import com.example.common.mapper.Mapper
import com.example.data.data.local.room.entity.CharacterModelEntity
import com.example.data.data.remote.dtos.character.CharacterModelDto
import com.example.domain.domain.models.CharacterModel
import com.example.presentation.models.CharacterUIModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideCharacterDto() = object : Mapper<CharacterModelDto, CharacterModel> {
        override fun toModel(value: CharacterModelDto)= CharacterModel (
            value.id,
            value.name,
            value.image,
            value.status,
            value.species,
            value.type,
            value.gender
            )

        override fun fromModel(value: CharacterModel)= CharacterModelDto (
            value.id,
            value.name,
            value.image,
            value.status,
            value.species,
            value.type,
            value.gender
        )

    }
    @Provides
    @Singleton
    fun provideCharacterMapperUI() = object : Mapper<CharacterModel, CharacterUIModel> {
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
    @Provides
    @Singleton
    fun provideCharacterMapperEntity() = object : Mapper<CharacterModelEntity,CharacterModel> {
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
}