package com.cwh.counterapp.data.repository

import com.cwh.counterapp.model.DhikrHistoryEntity
import com.cwh.counterapp.model.HistoryDao
import kotlinx.coroutines.flow.Flow

class HistoryRepository(
    private val historyDao: HistoryDao
) {

    val history: Flow<List<DhikrHistoryEntity>> =
        historyDao.getAllHistory()

    suspend fun addHistory(
        history: DhikrHistoryEntity
    ) {
        historyDao.insert(history)
    }

    suspend fun clearHistory() {
        historyDao.clearHistory()
    }
}