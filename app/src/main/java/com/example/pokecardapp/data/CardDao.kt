package com.example.pokecardapp.data

import android.content.ClipData
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

    @Update
    suspend fun update(card: Card)

    @Query("SELECT * from cards ORDER BY name ASC")
    fun getCards(): Flow<List<Card>>

    @Query("SELECT SUM(price) from cards")
    fun getTotalPrice(): Flow<Double>

    @Query("SELECT COUNT(*) FROM cards")
    fun getCardAmount(): Flow<Int>

}