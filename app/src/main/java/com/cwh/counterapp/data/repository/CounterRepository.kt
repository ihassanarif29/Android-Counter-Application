package com.cwh.counterapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "counter_preferences"
)

class CounterRepository(
    private val context: Context
) {

    companion object {
        private val COUNT_KEY = intPreferencesKey("count")
    }

    fun getCount(dhikrId: String): Flow<Int> {

        val countKey = intPreferencesKey(
            "count_$dhikrId"
        )

        return context.dataStore.data
            .map { preferences ->
                preferences[countKey] ?: 0
            }
    }

    suspend fun saveCount(
        dhikrId: String,
        count: Int
    ) {

        val countKey = intPreferencesKey(
            "count_$dhikrId"
        )

        context.dataStore.edit { preferences ->

            preferences[countKey] = count
        }
    }
}