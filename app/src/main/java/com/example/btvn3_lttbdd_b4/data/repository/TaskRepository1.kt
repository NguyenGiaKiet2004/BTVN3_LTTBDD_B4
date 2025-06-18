package com.example.btvn3_lttbdd_b4.data.repository

import com.example.btvn3_lttbdd_b4.data.api.RetrofitInstance
import com.example.btvn3_lttbdd_b4.data.model.Task


class TaskRepository {
    // TaskRepository.kt
    suspend fun getTasks(): List<Task> {
        val response = RetrofitInstance.taskApi.getTasks()
        return if (response.isSuccess) {
            response.data
        } else {
            emptyList()
        }
    }

}
