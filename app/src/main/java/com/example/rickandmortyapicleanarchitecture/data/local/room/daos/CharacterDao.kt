package com.example.rickandmortyapicleanarchitecture.data.local.room.daos

import androidx.room.*
import com.example.rickandmortyapicleanarchitecture.data.local.room.entity.CharacterModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_model")
    suspend fun fetchLocationCharacters(): List<CharacterModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterModelEntity:CharacterModelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterModelEntity: List<CharacterModelEntity>)

    @Update
    suspend fun updateCharacter(characterEntity: CharacterModelEntity)

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterModelEntity)

    @Query("DELETE FROM character_model")
    suspend fun clear()

    @Query("SELECT * FROM character_model WHERE name LIKE :searchQuery")
    fun searchCharacters(searchQuery: String): Flow<List<CharacterModelEntity>>

}