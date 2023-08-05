package com.example.rickandmortyapicleanarchitecture.data.repositories

import com.example.rickandmortyapicleanarchitecture.data.remote.apiservices.CharacterApiService
import com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character.toDomain
import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService,
) : CharacterRepository {

    override fun fetchCharacters() = flow<Either<String, List<CharacterModel>>> {
        emit(Either.Right(service.fetchCharacters().results.map {
            it.toDomain()
        }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "Error occurred"))
    }

    override fun fetchSingleCharacter(id: Int) = flow<Either<String, CharacterModel>> {
        emit(Either.Right(service.fetchSingleCharacter(id).toDomain()))

    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "Error occurred"))
    }
}


