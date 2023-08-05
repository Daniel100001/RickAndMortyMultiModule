package com.example.rickandmortyapicleanarchitecture.domain.repositories

import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacters() : Flow<Either<String, List<CharacterModel>>>

    fun fetchSingleCharacter(id: Int) : Flow<Either<String,CharacterModel>>
}