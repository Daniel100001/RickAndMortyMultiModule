package com.example.rickandmortyapicleanarchitecture.di

import com.example.rickandmortyapicleanarchitecture.data.repositories.CharacterRepositoryImpl
import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.domain.repositories.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindCharacterRepository(repositoryImpl: CharacterRepositoryImpl): CharacterRepository
}