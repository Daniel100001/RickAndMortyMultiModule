package com.example.rickandmortyapicleanarchitecture.presentation.ui.fragments.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.domain.usecase.FetchCharactersUseCase
import com.example.rickandmortyapicleanarchitecture.domain.usecase.FetchSingleCharacterUseCase
import com.example.rickandmortyapicleanarchitecture.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchSingleCharacterUseCase: FetchSingleCharacterUseCase
): ViewModel() {

    private val _characterState = MutableStateFlow<UIState<List<CharacterModel>>>(UIState.Loading())
    val charactersState = _characterState.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            fetchCharactersUseCase().collect{
                when (it){
                    is Either.Left -> {
                        it.message?.let {error ->
                            _characterState.value = UIState.Error(error)
                        }
                    }
                    is Either.Right -> {
                        it.data?.let{ characters ->
                            _characterState.value = UIState.Success(characters)
                        }
                    }
                }
            }
        }
    }

//    fun fetchSingleCharacter(id: Int) {
//        viewModelScope.launch {
//            fetchSingleCharacterUseCase(id).collect{
//                when(it){
//                    is Either.Left -> {
//                        it.message?.let {error ->
//                        _characterState.value = UIState.Error(error)
//                        }
//                    }
//                    is Either.Right -> {
//                        it.data?.let{ characters ->
//                            _characterState.value = UIState.Success(characters.map { characters ->
//                                characters.toUI()
//                            })
//                        }
//                    }
//                }
//            }
//        }
//    }
}