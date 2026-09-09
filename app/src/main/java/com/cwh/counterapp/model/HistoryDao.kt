package com.cwh.counterapp.model

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(history: DhikrHistoryEntity)

    @Query(
        "SELECT * FROM dhikr_history ORDER BY timestamp DESC"
    )
    fun getAllHistory(): Flow<List<DhikrHistoryEntity>>

    @Query("DELETE FROM dhikr_history")
    suspend fun clearHistory()
}