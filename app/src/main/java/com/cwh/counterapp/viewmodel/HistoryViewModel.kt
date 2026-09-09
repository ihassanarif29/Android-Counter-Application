package com.cwh.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwh.counterapp.data.repository.HistoryRepository
import com.cwh.counterapp.model.DhikrHistoryEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    repository: HistoryRepository
) : ViewModel() {

    val history: StateFlow<List<DhikrHistoryEntity>> =
        repository.history.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}