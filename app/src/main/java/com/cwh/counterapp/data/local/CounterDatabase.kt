package com.cwh.counterapp.data.local

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.cwh.counterapp.model.DhikrHistoryEntity
import com.cwh.counterapp.model.HistoryDao

@Database(
    entities = [DhikrHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TasbihDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {

        @Volatile
        private var INSTANCE: TasbihDatabase? = null

        fun getDatabase(context: Context): TasbihDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasbihDatabase::class.java,
                    "counter_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}