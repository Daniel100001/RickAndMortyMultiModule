package com.example.domain.domain.usecase.remote

import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke() = repository.fetchCharacters()
}