package com.example.pokecardapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// OnConflictStrategy.REPLACE
// https://stackoverflow.com/questions/48519896/room-update-or-insert-if-not-exist-rows-and-return-count-changed-rows

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card)

    @Delete
    suspend fun delete(card: Card)

    @Query("SELECT * from cards ORDER BY name ASC")
    fun getCards(): Flow<List<Card>>
}