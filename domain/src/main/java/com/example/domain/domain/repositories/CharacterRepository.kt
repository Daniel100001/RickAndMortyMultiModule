package com.example.domain.domain.repositories

import com.example.domain.domain.either.Either
import com.example.domain.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacters() : Flow<Either<String, List<CharacterModel>>>

    fun fetchSingleCharacter(id: Int) : Flow<Either<String, CharacterModel>>

    suspend fun insertCharacters(characters: List<CharacterModel>)

    fun fetchLocalCharacters(): Flow<Either<String, List<CharacterModel>>>

    suspend fun deleteCharacter(characterModel: CharacterModel)

    suspend fun updateCharacter(characterModel: CharacterModel)

    suspend fun clear()

    fun searchLocalCharacter(characterName: String) : Flow<List<CharacterModel>>
}