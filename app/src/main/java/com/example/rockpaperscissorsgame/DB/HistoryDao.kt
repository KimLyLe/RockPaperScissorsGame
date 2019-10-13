package com.example.rockpaperscissorsgame.DB

import androidx.room.*
import com.example.rockpaperscissorsgame.Model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM historyTable ORDER BY date DESC")
    suspend fun getAllHistories(): List<History>

    @Insert
    suspend fun insertHistory(history: History)

    @Query("DELETE FROM historyTable")
    suspend fun deleteHistory()

    @Update
    suspend fun updateHistory(history: History)

}
