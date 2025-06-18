package com.example.btvn3_lttbdd_b4.data.api

// File: app/src/main/java/com/your_app_name/api/TaskApiService.kt

import com.example.btvn3_lttbdd_b4.data.model.ApiResponse
import com.example.btvn3_lttbdd_b4.data.model.Task
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskService {
    @GET("tasks")
    suspend fun getTasks(): ApiResponse
}

