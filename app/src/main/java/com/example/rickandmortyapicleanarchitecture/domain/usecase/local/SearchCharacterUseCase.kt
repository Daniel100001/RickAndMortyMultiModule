package com.example.rickandmortyapicleanarchitecture.domain.usecase.local

import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(name : String) = repository.searchLocalCharacter(name)
}