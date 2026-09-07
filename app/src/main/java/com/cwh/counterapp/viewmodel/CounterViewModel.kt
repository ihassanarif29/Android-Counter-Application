package com.cwh.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwh.counterapp.data.repository.CounterRepository
import com.cwh.counterapp.model.Dhikr
import com.cwh.counterapp.model.defaultDhikrList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CounterViewModel (
    private val repository: CounterRepository
): ViewModel() {
    private val _selectedDhikr = MutableStateFlow(defaultDhikrList[0])
    val selectedDhikr: StateFlow<Dhikr> = _selectedDhikr.asStateFlow()
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    val target = 33

    init {
        loadCount()
    }

    private fun loadCount() {
        viewModelScope.launch {
            repository.count.collect { savedCount ->
                _count.value = savedCount
            }
        }
    }

    fun selectDhikr(dhikr: Dhikr) {
        _selectedDhikr.value = dhikr
        _count.value = 0
    }

    fun increment() {
        if (_count.value < target) {
            _count.value++
            saveCount()
        }
    }

    fun reset() {
        _count.value = 0
        saveCount()
    }

    private fun saveCount() {
        viewModelScope.launch {
            repository.saveCount(_count.value)
        }
    }
}