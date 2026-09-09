package com.cwh.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwh.counterapp.data.repository.CounterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CounterViewModel (
    private val repository: CounterRepository
): ViewModel() {
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