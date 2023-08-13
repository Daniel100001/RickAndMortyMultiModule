package com.example.rickandmortyapicleanarchitecture.domain.usecase.local

import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import javax.inject.Inject

class DeleteCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
     suspend operator fun invoke(characterModel: CharacterModel) =
        repository.deleteCharacter(characterModel)
}
