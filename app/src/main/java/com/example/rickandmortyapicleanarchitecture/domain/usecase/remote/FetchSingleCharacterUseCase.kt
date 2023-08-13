package com.example.rickandmortyapicleanarchitecture.domain.usecase.remote

import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchSingleCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(id: Int) = repository.fetchSingleCharacter(id = id)
}