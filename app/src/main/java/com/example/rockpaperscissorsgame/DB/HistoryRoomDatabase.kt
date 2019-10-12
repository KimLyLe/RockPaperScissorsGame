package com.example.rockpaperscissorsgame.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockpaperscissorsgame.Model.History

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryRoomDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var historyRoomDatabaseInstance: HistoryRoomDatabase? = null

        fun getDatabase(context: Context): HistoryRoomDatabase? {
            if (historyRoomDatabaseInstance == null) {
                synchronized(HistoryRoomDatabase::class.java) {
                    if (historyRoomDatabaseInstance == null) {
                        historyRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            HistoryRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return historyRoomDatabaseInstance
        }
    }

}
