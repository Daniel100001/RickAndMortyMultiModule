package com.example.domain.domain.usecase.remote

import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchSingleCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(id: Int) = repository.fetchSingleCharacter(id = id)
}