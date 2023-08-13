package com.example.rickandmortyapicleanarchitecture.domain.usecase.local

import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchLocalCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke() = characterRepository.fetchLocalCharacters()
}