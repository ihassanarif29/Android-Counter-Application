package com.cwh.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwh.counterapp.data.repository.CounterRepository
import com.cwh.counterapp.data.repository.HistoryRepository
import com.cwh.counterapp.model.Dhikr
import com.cwh.counterapp.model.DhikrHistoryEntity
import com.cwh.counterapp.model.defaultDhikrList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CounterViewModel (
    private val repository: CounterRepository,
    private val historyRepository: HistoryRepository
): ViewModel() {
    private val _selectedDhikr = MutableStateFlow(defaultDhikrList[0])
    val selectedDhikr: StateFlow<Dhikr> = _selectedDhikr.asStateFlow()
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    val target = 33

    private var countJob: Job? = null

    init {
        loadCount(defaultDhikrList[0])
    }

    private fun loadCount(dhikr: Dhikr) {

        countJob?.cancel()

        countJob = viewModelScope.launch {

            repository
                .getCount(dhikr.id)
                .collect { savedCount ->

                    _count.value = savedCount
                }
        }
    }

    fun selectDhikr(dhikr: Dhikr) {
        _selectedDhikr.value = dhikr
        loadCount(dhikr)
    }

    fun increment() {
        val dhikr = _selectedDhikr.value
        val target = dhikr.target

        if (_count.value < target) {
            _count.value++
            saveCount()
            if (_count.value == target) {
                saveHistory(dhikr)
            }
        }
    }

    private fun saveHistory(
        dhikr: Dhikr
    ) {
        viewModelScope.launch {

            historyRepository.addHistory(
                DhikrHistoryEntity(
                    dhikrId = dhikr.id,
                    dhikrName = dhikr.name,
                    count = dhikr.target,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun reset() {
        _count.value = 0
        saveCount()
    }

    private fun saveCount() {

        val dhikrId =
            _selectedDhikr.value.id

        val currentCount =
            _count.value

        viewModelScope.launch {

            repository.saveCount(
                dhikrId = dhikrId,
                count = currentCount
            )
        }
    }
}