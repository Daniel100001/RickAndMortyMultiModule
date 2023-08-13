package com.example.rickandmortyapicleanarchitecture.data.repositories

import com.example.rickandmortyapicleanarchitecture.data.local.room.daos.CharacterDao
import com.example.rickandmortyapicleanarchitecture.data.local.room.entity.toDomain
import com.example.rickandmortyapicleanarchitecture.data.remote.apiservices.CharacterApiService
import com.example.rickandmortyapicleanarchitecture.data.remote.dtos.character.toDomain
import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.domain.models.toEntity
import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import com.example.rickandmortyapicleanarchitecture.utils.mapper.CharacterEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService,
    private val characterDao: CharacterDao,
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

    override suspend fun insertCharacters(characters: List<CharacterModel>) {
        withContext(Dispatchers.IO) {
            characterDao.insertCharacters(characters.map {
                CharacterEntityMapper.fromModel(it)
            })
        }
    }

    override fun fetchLocalCharacters() = flow<Either<String, List<CharacterModel>>> {
        emit(Either.Right(characterDao.fetchLocationCharacters().map {
            CharacterEntityMapper.toModel(it)
        }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "Error"))
    }

    override suspend fun deleteCharacter(characterModel: CharacterModel) {
        withContext(Dispatchers.IO){
            characterDao.deleteCharacter(characterModel.toEntity())
        }
    }


    override suspend fun updateCharacter(characterModel: CharacterModel) {
        withContext(Dispatchers.IO) {
            characterDao.updateCharacter(characterModel.toEntity())
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            characterDao.clear()
        }
    }

    override fun searchLocalCharacter(characterName: String) =
        characterDao.searchCharacters(characterName).map {
            it.map { characterModel ->
                characterModel.toDomain()
            }
        }
}





