package com.example.rickandmortyapicleanarchitecture.presentation.ui.fragments.character.detail

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapicleanarchitecture.domain.usecase.remote.FetchSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val fetchSingleCharacterUseCase: FetchSingleCharacterUseCase
): ViewModel() {

    fun fetchSingleCharacter(id: Int) = fetchSingleCharacterUseCase(id)
}