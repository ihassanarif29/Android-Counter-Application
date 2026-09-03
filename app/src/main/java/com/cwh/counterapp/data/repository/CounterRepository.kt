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

    val count: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[COUNT_KEY] ?: 0
        }

    suspend fun saveCount(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[COUNT_KEY] = count
        }
    }
}