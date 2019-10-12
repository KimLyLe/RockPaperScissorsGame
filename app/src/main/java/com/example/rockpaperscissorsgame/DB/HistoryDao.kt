package com.example.rockpaperscissorsgame.DB

import androidx.room.*
import com.example.rockpaperscissorsgame.Model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM historyTable")
    suspend fun getAllHistories(): List<History>

    @Insert
    suspend fun insertHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

    @Update
    suspend fun updateHistory(history: History)

}
