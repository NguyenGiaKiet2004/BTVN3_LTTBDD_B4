package com.example.btvn3_lttbdd_b4.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btvn3_lttbdd_b4.data.model.Task
import com.example.btvn3_lttbdd_b4.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        Log.d("TaskViewModel", "Initializing ViewModel")
        fetchTasks()
    }

    fun fetchTasks() {
        Log.d("TaskViewModel", "Starting fetchTasks")
        viewModelScope.launch {
            try {
                Log.d("TaskViewModel", "Calling repository.getTasks")
                val response = repository.getTasks()
                Log.d("TaskViewModel", "Tasks fetched: ${response.size}")
                _tasks.value = response
                _error.value = null
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Error fetching tasks: ${e.message}", e)
                _tasks.value = emptyList()
                _error.value = e.message
            }
        }
    }
}