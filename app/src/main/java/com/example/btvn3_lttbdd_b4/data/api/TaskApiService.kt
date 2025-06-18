// File: app/src/main/java/com/your_app_name/api/TaskApiService.kt

package com.example.btvn3_lttbdd_b4.data.api

import com.example.btvn3_lttbdd_b4.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

}

