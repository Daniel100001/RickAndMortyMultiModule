package com.example.rickandmortyapicleanarchitecture.domain.usecase.remote

import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke() = repository.fetchCharacters()
}