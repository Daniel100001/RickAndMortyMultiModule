package com.example.rickandmortyapicleanarchitecture.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel

@Entity(tableName = "character_model")
data class CharacterModelEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String
) : java.io.Serializable

fun CharacterModel.toEntity() = CharacterModelEntity(
    id, name, image, status, species, type, gender
)

fun CharacterModelEntity.toDomain() = CharacterModel(
    id, name, image, status, species, type, gender
)



