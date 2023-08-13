package com.example.rickandmortyapicleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapicleanarchitecture.data.local.room.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideRickAndMortyDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, DataBase::class.java, "rick_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCharacterDao(dataBase: DataBase) = dataBase.characterDao()
}