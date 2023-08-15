package com.example.presentation.ui.fragments.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mapper.Mapper
import com.example.domain.domain.either.Either
import com.example.domain.domain.models.CharacterModel
import com.example.domain.domain.usecase.local.ClearAllCharactersUseCase
import com.example.domain.domain.usecase.local.DeleteCharacterUseCase
import com.example.domain.domain.usecase.local.FetchLocalCharactersUseCase
import com.example.domain.domain.usecase.local.InsertCharactersUseCase
import com.example.domain.domain.usecase.local.SearchCharacterUseCase
import com.example.domain.domain.usecase.local.UpdateCharacterUseCase
import com.example.domain.domain.usecase.remote.FetchCharactersUseCase
import com.example.presentation.models.CharacterUIModel
import com.example.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchLocalCharactersUseCase: FetchLocalCharactersUseCase,
    private val insertCharactersUseCase: InsertCharactersUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
    private val updaterCharacterUseCase: UpdateCharacterUseCase,
    private val clearAllCharactersUseCase: ClearAllCharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val characterMapperUI: Mapper<CharacterModel, CharacterUIModel>
) : ViewModel() {

    private val _characterState = MutableStateFlow<UIState<List<CharacterModel>>>(
        UIState.Loading())
    val charactersState = _characterState.asStateFlow()

    private val _localState = MutableStateFlow<UIState<List<CharacterUIModel>>>(UIState.Loading())
    val localState = _localState.asStateFlow()

    init {
        fetchCharacters()
        fetchLocalCharacters()

    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            fetchCharactersUseCase().collect {
                when (it) {
                    is Either.Left -> {
                        it.message?.let { error ->
                            _characterState.value = UIState.Error(error)
                        }
                    }
                    is Either.Right -> {
                        it.data?.let { characters ->
                            insertCharactersUseCase(characters)
                            _characterState.value = UIState.Success(characters)
                        }
                    }
                }
            }
        }
    }

    suspend fun deleteCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            deleteCharacterUseCase(characterModel)
        }
    }

    fun updateCharacter(characterModel: CharacterModel){
        viewModelScope.launch {
            updaterCharacterUseCase(characterModel)
        }
    }

    fun clear(){
        viewModelScope.launch {
            clearAllCharactersUseCase()
        }
    }

    fun searchCharacter(name: String)= searchCharacterUseCase(name)

    private fun fetchLocalCharacters() {
        viewModelScope.launch {
            fetchLocalCharactersUseCase().collect {
                when (it) {
                    is Either.Left -> {
                        if (it.message != null) {
                            _localState.value = UIState.Error(it.message!!)
                        } else {
                            Log.e("viewModel", "Error")
                        }
                    }
                    is Either.Right -> {
                        it.data?.let { characters ->
                            _localState.value = UIState.Success(characters.map { character ->
                                characterMapperUI.toModel(character)
                            })
                        }
                    }
                }
            }
        }
    }
}

