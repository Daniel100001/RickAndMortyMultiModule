package com.example.rickandmortyapicleanarchitecture.domain.usecase.local

import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class ClearAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke() =
        repository.clear()
}