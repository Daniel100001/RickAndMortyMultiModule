package com.example.domain.domain.usecase.local

import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class ClearAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke() = repository.clear()
}