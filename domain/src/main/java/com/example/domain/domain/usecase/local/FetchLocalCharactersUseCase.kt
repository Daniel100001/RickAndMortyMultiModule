package com.example.domain.domain.usecase.local

import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchLocalCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke() = characterRepository.fetchLocalCharacters()
}