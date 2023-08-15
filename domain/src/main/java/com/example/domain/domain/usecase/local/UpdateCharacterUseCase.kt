package com.example.domain.domain.usecase.local

import com.example.domain.domain.models.CharacterModel
import com.example.domain.domain.repositories.CharacterRepository
import javax.inject.Inject

class UpdateCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend operator fun invoke(characterModel: CharacterModel) = repository.updateCharacter(characterModel)
}