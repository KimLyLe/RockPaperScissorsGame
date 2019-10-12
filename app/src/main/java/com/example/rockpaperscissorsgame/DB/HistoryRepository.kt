package com.example.rockpaperscissorsgame.DB

import android.content.Context
import com.example.rockpaperscissorsgame.Model.History

public class HistoryRepository(context: Context) {

    private var historyDao: HistoryDao

    init {
        val reminderRoomDatabase = HistoryRoomDatabase.getDatabase(context)
        historyDao = reminderRoomDatabase!!.historyDao()
    }

    suspend fun getAllHistories(): List<History> {
        return historyDao.getAllHistories()
    }

    suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    suspend fun deleteHistory(history: History) {
        historyDao.deleteHistory(history)
    }

    suspend fun updateHistory(history: History) {
        historyDao.updateHistory(history)
    }

}
