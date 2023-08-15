package com.example.domain.domain.usecase.local

import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(name : String) = repository.searchLocalCharacter(name)
}